package com.sistempdv.pdv.record;

import java.time.Instant;

public record CustomError(Instant timestamp, Integer status, String error, String path) {
}
