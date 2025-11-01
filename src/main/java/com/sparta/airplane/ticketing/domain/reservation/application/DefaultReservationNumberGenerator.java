package com.sparta.airplane.ticketing.domain.reservation.application;

import com.sparta.airplane.ticketing.domain.reservation.domain.ReservationNumberGenerator;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class DefaultReservationNumberGenerator implements ReservationNumberGenerator {
    private static final char[] POOL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int LENGTH = 6;

    private final Random random = new SecureRandom();

    @Override
    public ReservationNumber generate() {
        char[] buf = new char[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            buf[i] = POOL[random.nextInt(POOL.length)];
        }
        return new ReservationNumber(new String(buf));
    }
}
