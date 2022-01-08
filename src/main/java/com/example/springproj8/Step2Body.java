package com.example.springproj8;

import java.math.BigInteger;

public class Step2Body {
    public BigInteger A;
    public BigInteger M1;

    public Step2Body(BigInteger A, BigInteger M1) {
        this.A = A;
        this.M1 = M1;
    }

    @Override
    public String toString() {
        return String.format("Step2Body<A=%s, M1=%s>", A, M1);
    }
}