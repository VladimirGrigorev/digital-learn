package com.netcracker.project.service.impl;
import com.netcracker.project.entity.Cart;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.User;
import com.netcracker.project.exception.BadRequestException;
import com.netcracker.project.repository.CartRepository;
import com.netcracker.project.repository.TrainingRepository;
import com.netcracker.project.repository.UserRepository;
import com.netcracker.project.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    /**
     * Beans for work with cart
     */
    private final TrainingRepository trainingRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    /**
     * Constructor
     */
    public CartServiceImpl(CartRepository cartRepository,
                           UserRepository userRepository,
                           TrainingRepository trainingRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    /**
     * Find all carts
     * @return list carts
     */
    @Override
    public List<Cart> findAll() {
        log.info("Cart were received.");
        return cartRepository.findAll();
    }

    /**
     * Find user cart
     * @param userLogin - username
     * @return cart or null
     */
    @Override
    public Optional<Cart> findUserCart(String userLogin) {
        if (userRepository.findByEmail(userLogin).isPresent()) {
            User user = userRepository.findByEmail(userLogin).get();
            log.info("Cart were received.");
            return cartRepository.findById(user.getId());
        } else {
            log.warn("Impossible to find user cart.");
            return Optional.empty();
        }
    }

    /**
     * Add training
     * @param trainingId - training id
     * @param userLogin - username
     */
    @Override
    public void addTraining(UUID trainingId, String userLogin) {
        if(userRepository.findByEmail(userLogin).isPresent()
                && cartRepository.findById(userRepository.findByEmail(userLogin).get().getId()).isPresent()
                && trainingRepository.findById(trainingId).isPresent()) {
            Cart cart = cartRepository.findById(userRepository.findByEmail(userLogin).get().getId()).get();
            List<Training> trainings = cart.getTrainings();

            if(trainings.indexOf(trainingRepository.findById(trainingId).get()) != -1)
                throw new BadRequestException("Этот курс уже был добавлен");

            trainings.add(trainingRepository.findById(trainingId).get());
            cart.setTrainings(trainings);
            cartRepository.save(cart);
            log.info("Training was successfully added to cart.");
        }
    }

    /**
     * Delete training
     * @param trainingId - training id
     * @param userLogin - username
     */
    @Override
    public void deleteTraining(UUID trainingId, String userLogin) {
        if(userRepository.findByEmail(userLogin).isPresent()
                && cartRepository.findById(userRepository.findByEmail(userLogin).get().getId()).isPresent()
                && trainingRepository.findById(trainingId).isPresent()) {
            Cart cart = cartRepository.findById(userRepository.findByEmail(userLogin).get().getId()).get();
            List<Training> trainings = cart.getTrainings();
            trainings.remove(trainingRepository.findById(trainingId).get());
            cart.setTrainings(trainings);
            cartRepository.save(cart);
            log.info("Training was successfully deleted.");
        }
    }

    /**
     * Get total price
     * @param cart - cart
     * @return total price
     */
    @Override
    public double getTotalPrice(Cart cart){
        List<Training> trainings = cart.getTrainings();
        double totalPrice = 0;
        for (Training training : trainings) {
            totalPrice += training.getPrice();
        }
        return totalPrice;
    }

    /**
     * Clear cart
     * @param name - username
     */
    @Override
    public void clear(String userLogin) {
        if(userRepository.findByEmail(userLogin).isPresent()
                && cartRepository.findById(userRepository.findByEmail(userLogin).get().getId()).isPresent()) {
            Cart cart = cartRepository.findById(userRepository.findByEmail(userLogin).get().getId()).get();
            List<Training> trainings = cart.getTrainings();
            trainings.clear();
            cart.setTrainings(trainings);
            cartRepository.save(cart);
            log.info("Trainings were successfully clear.");
        }
    }
}
