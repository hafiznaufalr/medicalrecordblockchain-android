package my.id.medicalrecordblockchain.data.repository.user

import my.id.medicalrecordblockchain.data.requests.SignInRequest
import my.id.medicalrecordblockchain.data.requests.SignUpRequest
import my.id.medicalrecordblockchain.data.response.AccountResponse
import my.id.medicalrecordblockchain.data.response.SignInResponse
import my.id.medicalrecordblockchain.utils.ResultData

interface UserRepository {
    suspend fun postSignIn(signInRequest: SignInRequest): ResultData<SignInResponse>
    suspend fun postSignUp(signUpRequest: SignUpRequest): ResultData<SignInResponse>
    suspend fun getAccount(): ResultData<AccountResponse>
}