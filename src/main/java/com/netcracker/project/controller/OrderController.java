package com.netcracker.project.controller;

import com.netcracker.project.entity.Order;
import com.netcracker.project.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller for order
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    /**
     * Order service
     */
    private final OrderService orderService;

    /**
     * Constructor
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Gets order by user
     * @param principal - user
     * @return order
     */
    @GetMapping("/getOrder")
    public ResponseEntity<Order> getOrder(Principal principal) {
        return orderService.getOrder(principal.getName()).map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    /**
     * Delete all orders by user
     * @param principal - user
     * @return message about result
     */
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders(Principal principal) {
        if (orderService.findAll(principal.getName()) == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(orderService.findAll(principal.getName()));
    }

    /**
     * Gets all user orders
     * @param userId - user id
     * @return list orders
     */
    @GetMapping("/{userId}")
    public List<Order> getAllUserOrders(@PathVariable UUID userId) {
        return orderService.findAllUserOrders(userId);
    }

    /**
     * Add order
     * @param order - order
     * @param principal - user
     * @return order
     */
    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, Principal principal) {
        Optional<Order> optionalOrder = orderService.saveOrder(order, principal.getName());
        return optionalOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete order
     * @param id - order id
     * @param principal - user
     * @return order
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable UUID id, Principal principal) {
        Optional<Order> order = orderService.deleteOrder(id, principal.getName());
        return order.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
}
