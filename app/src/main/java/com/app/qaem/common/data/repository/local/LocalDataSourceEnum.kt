package com.app.qaem.common.data.repository.local

import com.app.qaem.common.domain.repository.local.ILocalDataSourceEnum

enum class LocalDataSourceEnum(override val keyValue: String) : ILocalDataSourceEnum {
    ACCESS_TOKEN("access_token"),
    USER("user"),
}