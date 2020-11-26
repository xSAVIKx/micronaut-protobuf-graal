/*
 * Copyright 2020, TeamDev. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Redistribution and use in source and/or binary forms, with or without
 * modification, must retain the above copyright notice and the following
 * disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.yurisergiichuk.graalvm;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.TextFormat;
import com.google.protobuf.util.JsonFormat;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.protobuf.codec.ProtobufferCodec;

@Controller("/example")
final class ProtoController {

    @Get(value = "proto", produces = ProtobufferCodec.PROTOBUFFER_ENCODED)
    ProtobufExampleReply proto() {
        var response = ProtobufExampleReply
                .newBuilder()
                .setMessage("proto")
                .build();
        return response;
    }

    @Get(value = "json", produces = MediaType.APPLICATION_JSON)
    String json() throws InvalidProtocolBufferException {
        var proto = ProtobufExampleReply
                .newBuilder()
                .setMessage("json")
                .build();
        var response = JsonFormat.printer().print(proto);
        return response;
    }

    @Get(value = "text", produces = MediaType.TEXT_PLAIN)
    String text() {
        var proto = ProtobufExampleReply
                .newBuilder()
                .setMessage("json")
                .build();
        var response = TextFormat.printer().printToString(proto);
        return response;
    }
}
