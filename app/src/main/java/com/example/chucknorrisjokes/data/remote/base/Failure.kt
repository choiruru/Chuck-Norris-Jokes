package com.example.chucknorrisjokes.data.remote.base

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
data class Failure(val code: Int, val msg: String) : Throwable()
