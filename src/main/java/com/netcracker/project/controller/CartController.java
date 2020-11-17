package com.netcracker.project.controller;

import com.netcracker.project.entity.Cart;
import com.netcracker.project.service.CartService;
import com.netcracker.project.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Controller for cart
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    /**
     * Cart service
     */
    private final CartService cartService;

    /**
     * Constructor
     */
    @Autowired
    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    /**
     * Gets all carts
     * @return all carts
     */
    @GetMapping("/all")
    public List<Cart> getAllCarts() {
        return cartService.findAll();
    }

    /**
     * Gets user cart
     * @param principal - user
     * @return user cart
     */
    @GetMapping
    public ResponseEntity<Cart> getUserCart(Principal principal) {
        return cartService.findUserCart(principal.getName()).map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Add training to user cart
     * @param trainingId - training id
     * @param principal - user
     * @return message about result
     */
    @PostMapping
    public ResponseEntity<String> addTrainingToUserCart(@RequestBody String trainingId,
                                                        Principal principal){
        cartService.addTraining(UUID.fromString(trainingId), principal.getName());
        return ResponseEntity.ok().build();
    }

    /**
     * Delete training by id
     * @param trainingId - training id
     * @param principal - user
     * @return message about result
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTraining(@PathVariable("id") String trainingId,
                                                 Principal principal) {
        cartService.deleteTraining(UUID.fromString(trainingId), principal.getName());
        return ResponseEntity.ok().build();
    }

    /**
     * Gets total price cart
     * @param principal - user
     * @return total price cart
     */
    @GetMapping("/totalPrice")
    public ResponseEntity<?> getTotalPrice(Principal principal) {
        if(cartService.findUserCart(principal.getName()).isPresent()) {
            return ResponseEntity.ok(cartService.getTotalPrice(cartService.findUserCart(principal.getName()).get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Delete all in cart
     * @param principal - user
     * @return message about result
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteTraining(Principal principal) {
        cartService.clear(principal.getName());
        return ResponseEntity.ok().build();
    }
}
