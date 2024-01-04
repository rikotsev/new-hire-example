package com.cellpointdigital.example.process.workflow

import io.temporal.common.converter.DefaultDataConverter
import io.temporal.common.converter.ProtobufPayloadConverter

class ExtendedDataConverter : DefaultDataConverter(
    ProtobufPayloadConverter()
) {
}