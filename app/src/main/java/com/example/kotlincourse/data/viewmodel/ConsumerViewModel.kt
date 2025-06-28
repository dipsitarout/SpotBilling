package com.example.kotlincourse.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincourse.data.local.AppDatabase
import com.example.kotlincourse.data.local.ConsumerResponseEntity
import com.example.kotlincourse.data.model.ConsumerRequest
import com.example.kotlincourse.data.repository.ConsumerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConsumerViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).consumerResponseDao()
    private val repository = ConsumerRepository() // ✅ No argument needed now

    private val _consumerData = MutableStateFlow<ConsumerResponseEntity?>(null)
    val consumerData: StateFlow<ConsumerResponseEntity?> = _consumerData

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun insertConsumerData(entity: ConsumerResponseEntity) {
        viewModelScope.launch {
            try {
                dao.insert(entity)
                _consumerData.value = entity
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }

    fun loadConsumerData() {
        viewModelScope.launch {
            try {
                val data = dao.getConsumerResponse()
                _consumerData.value = data
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load data"
            }
        }
    }
    fun getconsumerData(consumerNumber: String, refNo: String, billType: String, userId: String) {
        viewModelScope.launch {
            try {
                val request = ConsumerRequest(
                    ConsumerNumber = consumerNumber,
                    ref_no = refNo,
                    bill_type = billType,
                    usrid = userId
                )
                val response = repository.getConsumerData(request)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (!body.isNullOrEmpty()) {
                        val firstItem = body[0]
                        val entity = ConsumerResponseEntity(
                            KNO = firstItem.KNO,
                            CHK_DGT = firstItem.CHK_DGT,
                            CNSMR_NM = firstItem.CNSMR_NM,
                            MDDL_NM = firstItem.MDDL_NM,
                            LST_NM = firstItem.LST_NM,
                            ADD1 = firstItem.ADD1,
                            ADD2 = firstItem.ADD2,
                            BLL_NO = firstItem.BLL_NO,
                            BLL_MNTH = firstItem.BLL_MNTH,
                            BLL_YR = firstItem.BLL_YR,
                            CRRNT_DMND = firstItem.CRRNT_DMND,
                            CD_PERCENTAGE = firstItem.CD_PERCENTAGE,
                            DUE_AMNT = firstItem.DUE_AMNT,
                            AMNT_PYBL_RNDD = firstItem.AMNT_PYBL_RNDD,
                            DUE30_AMNT = firstItem.DUE30_AMNT,
                            DUE60_AMNT = firstItem.DUE60_AMNT,
                            BLL_DT = firstItem.BLL_DT,
                            DUE_DT = firstItem.DUE_DT,
                            CRCL_CD = firstItem.CRCL_CD,
                            CYCL_NO = firstItem.CYCL_NO,
                            DSTRCT_CD = firstItem.DSTRCT_CD,
                            ZN_CD = firstItem.ZN_CD,
                            INSRTD_DT = firstItem.INSRTD_DT,
                            UPDTD_DT = firstItem.UPDTD_DT,
                            STP_BLL_RSN_CD = firstItem.STP_BLL_RSN_CD,
                            BK_NO = firstItem.BK_NO,
                            CNSMR_TYP_CD = firstItem.CNSMR_TYP_CD,
                            SPPLY_TYP_CD = firstItem.SPPLY_TYP_CD,
                            HRB_FLG = firstItem.HRB_FLG,
                            SLB = firstItem.SLB,
                            LST_ENRGY_PYMNT_AMNT = firstItem.LST_ENRGY_PYMNT_AMNT,
                            CRRNT_RDNG_DT = firstItem.CRRNT_RDNG_DT,
                            SRC_SYSTM = firstItem.SRC_SYSTM,
                            UTILITY_ID = firstItem.UTILITY_ID,
                            NETIA_FLAG = firstItem.NETIA_FLAG,
                            BUSINESS_PRTNR = firstItem.BUSINESS_PRTNR,
                            CNTRCT_ACCNT = firstItem.CNTRCT_ACCNT,
                            BILLNG_CLASS = firstItem.BILLNG_CLASS,
                            CONN_TYP = firstItem.CONN_TYP,
                            MISUSE_TYP = firstItem.MISUSE_TYP,
                            RATE_CATEGORY = firstItem.RATE_CATEGORY,
                            TEMP_CONN_FLG = firstItem.TEMP_CONN_FLG,
                            BL_RBT_DT = firstItem.BL_RBT_DT,
                            BL_RBT_AMT = firstItem.BL_RBT_AMT,
                            MOBILE_NO = firstItem.MOBILE_NO,
                            EMAIL_ID = firstItem.EMAIL_ID,
                            DISTRICT_CODE = firstItem.DISTRICT_CODE,
                            CONSUMER_TYPE = firstItem.CONSUMER_TYPE,
                            LEGACY_KNO = firstItem.LEGACY_KNO,
                            DN_No = firstItem.DN_No,
                            DN_SD = firstItem.DN_SD,
                            DN_Total = firstItem.DN_Total,
                            Payable_Amt = firstItem.Payable_Amt,
                            srvdatetime = firstItem.srvdatetime,
                            julandt = firstItem.julandt,
                            Status = firstItem.Status,
                            Message = firstItem.Message,
                            USER_ID = firstItem.USER_ID,
                            RECORD_STATUS = firstItem.RECORD_STATUS,
                            MAXIMUM_AMOUNT = firstItem.MAXIMUM_AMOUNT,
                            ChequeAllowed = firstItem.ChequeAllowed,
                            COLL_Version = firstItem.COLL_Version,
                            Bill_Version = firstItem.Bill_Version,
                            AndroidID = firstItem.AndroidID,
                            VersionFlag = firstItem.VersionFlag,
                            DeviceFlag = firstItem.DeviceFlag,
                            ISSUE_TO = firstItem.ISSUE_TO,
                            COLL_Url = firstItem.COLL_Url,
                            BILL_Url = firstItem.BILL_Url,
                            Digital_Rebate = firstItem.Digital_Rebate,
                            IsPrepaid = firstItem.IsPrepaid,
                            PP_SIM_BILL_BIS = firstItem.PP_SIM_BILL_BIS,
                            PP_SIM_NILL_AB = firstItem.PP_SIM_NILL_AB,
                            PP_NET_BAL = firstItem.PP_NET_BAL,
                            PP_Vkont = firstItem.PP_Vkont,
                            PP_CONSUMPTION = firstItem.PP_CONSUMPTION,
                            PP_SPLY_STTS = firstItem.PP_SPLY_STTS,
                            PP_THRSHLD_BAL = firstItem.PP_THRSHLD_BAL,
                            LIMIT_CHECK = firstItem.LIMIT_CHECK,
                            MINIMUM_AMNT = firstItem.MINIMUM_AMNT,
                            NOTPAID_CNT = firstItem.NOTPAID_CNT,
                            LAST_PAYMENT_AMOUNT = firstItem.LAST_PAYMENT_AMOUNT,
                            LAST_PAYMENT_DATE = firstItem.LAST_PAYMENT_DATE,
                            DAY_CASH_AMOUNT = firstItem.DAY_CASH_AMOUNT,
                            SYS_Payable_Amt = firstItem.SYS_Payable_Amt,
                            PYMNT_AFT_D_RBT = firstItem.PYMNT_AFT_D_RBT,
                            SEC_MOBILE_NO = firstItem.SEC_MOBILE_NO,
                            Classification = firstItem.Classification,
                            ECL_AMT = firstItem.ECL_AMT
                        )
                        dao.insert(entity)
                        _consumerData.value = entity
                        _error.value = null
                    } else {
                        _error.value = "Empty response list"
                    }
                } else {
                    _error.value = "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to fetch consumer data"
            }
        }
    }



    fun clearConsumerData() {
        viewModelScope.launch {
            try {
                dao.clearAll()
                _consumerData.value = null
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to clear data"
            }
        }
    }
}
