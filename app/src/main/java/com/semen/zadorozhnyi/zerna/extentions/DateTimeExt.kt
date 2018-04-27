package com.semen.zadorozhnyi.zerna.extentions

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


fun DateTime.toDateTimeString(): String = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm").print(this)