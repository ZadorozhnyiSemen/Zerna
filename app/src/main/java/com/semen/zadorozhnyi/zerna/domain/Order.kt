package com.semen.zadorozhnyi.zerna.domain

import org.joda.time.DateTime
import java.util.Date

data class Order(
        var info: OrderInfo = OrderInfo(DateTime.now(), false),
        var userInfo: UserInfo = UserInfo(),
        var items: MutableList<Item> = mutableListOf()
)
