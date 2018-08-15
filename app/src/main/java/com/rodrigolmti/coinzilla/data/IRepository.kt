package com.rodrigolmti.coinzilla.data

import com.rodrigolmti.coinzilla.data.local.db.IDatabaseHelper
import com.rodrigolmti.coinzilla.data.local.prefs.IPreferencesHelper
import com.rodrigolmti.coinzilla.data.remote.IApiHelper

interface IRepository : IApiHelper, IPreferencesHelper, IDatabaseHelper {


}