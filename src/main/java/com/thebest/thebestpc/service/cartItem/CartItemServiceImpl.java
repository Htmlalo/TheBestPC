package com.thebest.thebestpc.service.cartItem;

import com.thebest.thebestpc.dto.CartCookieDto;
import com.thebest.thebestpc.mapper.CartItemsMapper;
import com.thebest.thebestpc.model.Cart;
import com.thebest.thebestpc.model.CartItem;
import com.thebest.thebestpc.model.Product;
import com.thebest.thebestpc.repository.CartItemRepository;
import com.thebest.thebestpc.service.Cookie.CookieService;
import com.thebest.thebestpc.service.cart.CartServiceImpl;
import com.thebest.thebestpc.service.product.ProductServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CartItemServiceImpl implements CartItemService {


    private final CartItemRepository cartItemRepository;
    private final CookieService cookieService;
    private final CartItemsMapper cartItemsMapper;
    private final ProductServiceImpl productServiceImpl;



    public CartItemServiceImpl(CartItemRepository cartItemRepository, CookieService cookieService, CartItemsMapper cartItemsMapper, ProductServiceImpl productServiceImpl) {
        this.cartItemRepository = cartItemRepository;
        this.cookieService = cookieService;
        this.cartItemsMapper = cartItemsMapper;
        this.productServiceImpl = productServiceImpl;

    }

    public void addCartItem(Cart cart, Product product) {
        addCartItem(cart, product, 1);
    }

    @Override
    public void addCartItem(Cart cart, Product product, int quantity) {
        CartItem cartItem = findByCartIdAndProductId(cart.getId(), product.getId());
        if (cartItem != null) {
            updateQuantityCartItem(cart, product);
            return;
        }
        cartItemRepository.save(CartItem.builder().cart(cart).product(product).quantity(quantity).build());
    }

    public void removeCartItem(String userId, Long productId) {

    }

    @Override
    @Transactional
    public void removeAllCartItem(String cartId) {
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);
        cartItemRepository.deleteAll(cartItems);
    }


    public void updateQuantityCartItem(Cart cart, Product product) {
        cartItemRepository.updateQuantityCartItem(cart.getId(), product.getId(), null);
    }

    @Override
    public void updateQuantityCartItem(Cart cart, Product product, int quantity) {
        cartItemRepository.updateQuantityCartItem(cart.getId(), product.getId(), quantity);
    }

    @Override
    public List<CartItem> getCartItems(String cartId) {
        return cartItemRepository.findAllByCartId(cartId);
    }

    @Override
    public CartItem findByCartIdAndProductId(String cartId, Long productId) {
        return cartItemRepository.findCartItemByCartAndProduct(cartId, productId).orElse(null);
    }

    @Override
    public void addCartItemToCookie(String key, List<Object> value) {
        cookieService.addCookieJson(key, value);
    }

    @Override
    public List<CartItem> getCartItemsFromCookie(String key) {
        List<CartCookieDto> cartCookieDtos = cookieService.getCookiesFromJson(key, CartCookieDto.class);
        List<CartItem> cartItems = new ArrayList<>(List.of());
        cartCookieDtos.forEach(cartCookieDto -> {
            CartItem cartItem = cartItemsMapper.mapToEntity(cartCookieDto);
            Product product = productServiceImpl.findById(cartCookieDto.getProductId());
            cartItem.setProduct(product);
            cartItems.add(cartItem);
        });
        return cartItems;
    }

    @Override
    public void addOrUpdateCartItemToCookie(String key, CartCookieDto value) {
        List<CartCookieDto> cartCookieDtos = cookieService.getCookiesFromJson(key, CartCookieDto.class);
        if (cartCookieDtos != null) {
            for (CartCookieDto cartItem : cartCookieDtos) {
                if (cartItem.getProductId().equals(value.getProductId())) {
                    cartItem.setQuantity(cartItem.getQuantity() + value.getQuantity());
                    cookieService.addCookieJson(key, cartCookieDtos);
                    return;
                }
            }
            cartCookieDtos.add(value);
            cookieService.addCookieJson(key, cartCookieDtos);
        } else {
            cookieService.addCookieJson(key, List.of(value));
        }
    }
}


