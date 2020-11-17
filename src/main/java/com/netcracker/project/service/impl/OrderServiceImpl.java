package com.netcracker.project.service.impl;

import com.netcracker.project.entity.Cart;
import com.netcracker.project.entity.Order;
import com.netcracker.project.entity.Role;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.enums.RoleName;
import com.netcracker.project.repository.CartRepository;
import com.netcracker.project.repository.OrderRepository;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * Beans for work with order
     */
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    /**
     * Constructor
     */
    public OrderServiceImpl(OrderRepository orderRepository,
                            CartRepository cartRepository,
                            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    /**
     * Check is user is admin
     * @param userLogin - username
     * @return boolean
     */
    private boolean isAdmin(String userLogin) {
        if (userRepository.findByEmail(userLogin).isPresent()) {
            for (Role role : userRepository.findByEmail(userLogin).get().getRoles()) {
                if (role.getName().toString().equals(RoleName.ROLE_ADMIN.toString())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Save order
     * @param order - order
     * @param userLogin - username
     * @return order
     */
    @Override
    public Optional<Order> saveOrder(Order order, String userLogin) {
        order.setUser(userRepository.findByEmail(userLogin).get());
        order.setOrderDate(LocalDate.now());
        if (order.getUser().getPurchasedTrainings() == null) {
            order.getUser().setPurchasedTrainings(new ArrayList<>());
        }
        for (Training purchasedTraining : order.getTrainings()) {
            if (!order.getUser().getPurchasedTrainings().contains(purchasedTraining.getId())) {
                order.getUser().getPurchasedTrainings().add(purchasedTraining.getId());
            }
        }
        cartRepository.save(order.getUser().getCart().clear());
            userRepository.save(order.getUser());
            return Optional.of(orderRepository.save(order));
    }

    /**
     * Delete order
     * @param orderId - order id
     * @param userLogin - username
     * @return order
     */
    @Override
    public Optional<Order> deleteOrder(UUID orderId, String userLogin) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (isAdmin(userLogin) && order.isPresent()) {
            orderRepository.delete(order.get());
            return order;
        }
        return Optional.empty();
    }

    /**
     * Find all
     * @param userLogin - username
     * @return order
     */
    @Override
    public List<Order> findAll(String userLogin) {
        if (isAdmin(userLogin)) {
            return orderRepository.findAll();
        }
        return null;
    }

    /**
     * Find all user orders
     * @param userId - user id
     * @return order
     */
    @Override
    public List<Order> findAllUserOrders(UUID userId) {
        return orderRepository.findByUser(userRepository.findById(userId).get());
    }

    /**
     * Get order
     * @param userLogin - username
     * @return order
     */
    @Override
    public Optional<Order> getOrder(String userLogin) {
        List<Training> cartTrainings = cartRepository.findById(userRepository
                .findByEmail(userLogin).get().getId()).get().getTrainings();

        if (!cartTrainings.isEmpty()) {
            Order order = new Order(cartTrainings, userRepository.findByEmail(userLogin).get());
            Cart cart = cartRepository.findById(userRepository
                    .findByEmail(userLogin).get().getId()).get();
            cart.setTrainings(new ArrayList<Training>());
            cartRepository.save(cart);
            return Optional.of(order);
        }

        return Optional.empty();
    }
}
