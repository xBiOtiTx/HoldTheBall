package ru.belyaev.holdtheball.effect;

import ru.belyaev.holdtheball.Ball;

// эффекты
// 1. По длительности: перманент, временные, мгновенные
// 2. По воздействию: нарастающие, циклические(периодические), одноразовые, комбинированные
public interface Effect {
    void apply(Ball ball, float deltaTime);
}
