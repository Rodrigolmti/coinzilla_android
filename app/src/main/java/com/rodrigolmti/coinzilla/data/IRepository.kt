package com.rodrigolmti.coinzilla.data

import com.rodrigolmti.coinzilla.data.local.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.remote.IApiHelper

interface IRepository : IApiHelper, IPreferencesHelper