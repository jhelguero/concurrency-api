package com.java.night.demo.Model;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Either<L, R> {

    final class Left<L0, R0> implements Either<L0, R0> {
        
        L0 left;

        private Left(L0 left) {
            this.left = left;
        }

        @Override
        public L0 getLeft() {
            return left;
        }

        @Override
        public R0 getRight() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public <L1> Either<L1, R0> mapLeft(Function<L0, L1> function) {
            return new Left<>(function.apply(left));
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R1> Either<L0, R1> mapRight(Function<R0, R1> function) {
            return (Either<L0, R1>) this;
        }

        @Override
        public Either<L0, R0> acceptLeft(Consumer<L0> consumer) {
            consumer.accept(left);
            return this;
        }

        @Override
        public Either<L0, R0> acceptRight(Consumer<R0> consumer) {
            return this;
        }

        @Override
        public Either<R0, L0> swap() {
            return new Right<>(left);
        }

        @Override
        public <T> T fold(Function<L0, T> leftFunction, Function<R0, T> rightFunction) {
            return leftFunction.apply(left);
        }
    }

    final class Right<L0, R0> implements Either<L0, R0> {

        R0 right;

        private Right(R0 right) {
            this.right = right;
        }

        @Override
        public L0 getLeft() {
            throw new UnsupportedOperationException();
        }

        @Override
        public R0 getRight() {
            return right;
        }

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <L1> Either<L1, R0> mapLeft(Function<L0, L1> function) {
            return (Either<L1, R0>) this;
        }

        @Override
        public <R1> Either<L0, R1> mapRight(Function<R0, R1> function) {
            return new Right<>(function.apply(right));
        }

        @Override
        public Either<L0, R0> acceptLeft(Consumer<L0> consumer) {
            return this;
        }

        @Override
        public Either<L0, R0> acceptRight(Consumer<R0> consumer) {
            consumer.accept(right);
            return this;
        }

        @Override
        public Either<R0, L0> swap() {
            return new Left<>(right);
        }

        @Override
        public <T> T fold(Function<L0, T> leftFunction, Function<R0, T> rightFunction) {
            return rightFunction.apply(right);
        }
    }

    static <L0, R0> Either<L0, R0> left(L0 left) {
        return new Left<>(left);
    }

    static <L0, R0> Either<L0, R0> right(R0 right) {
        return new Right<>(right);
    }

    L getLeft();

    R getRight();

    boolean isLeft();

    boolean isRight();

    <L0> Either<L0, R> mapLeft(Function<L, L0> function);

    <R0> Either<L, R0> mapRight(Function<R, R0> function);

    Either<L, R> acceptLeft(Consumer<L> consumer);

    Either<L, R> acceptRight(Consumer<R> consumer);

    Either<R, L> swap();

    <T> T fold(Function<L, T> leftFunction, Function<R, T> rightFunction);
}
