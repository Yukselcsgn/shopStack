package com.shopstack.order.service.impl;

import com.shopstack.order.domain.Order;
import com.shopstack.order.domain.OrderStatus;
import com.shopstack.order.dto.OrderRequest;
import com.shopstack.order.dto.OrderResponse;
import com.shopstack.order.mapper.OrderMapper;
import com.shopstack.order.repository.OrderRepository;
import com.shopstack.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponse createOrder(OrderRequest request){
        Order order = orderMapper.toEntity(request);
        orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponse getOrderById(UUID id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: "+id));
        return orderMapper.toResponse(order);
    }

    @Override
    public List<OrderResponse> getOrdersByCustomer(UUID customerId){
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    @Override
    public void cancelOrder(UUID id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeExpection("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

}