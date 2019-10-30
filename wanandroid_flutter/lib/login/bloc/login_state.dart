class LoginState {

  final bool isNameValid;
  final bool isPasswordValid;
  final bool isSubmitting;
  final bool isSuccess;
  final bool isFail;

  bool get isFormValid => isNameValid && isPasswordValid;

  LoginState({
    this.isNameValid,
    this.isPasswordValid,
    this.isSubmitting,
    this.isSuccess, 
    this.isFail});
  
  
  factory LoginState.empty(){
    return LoginState(
      isNameValid: true,
      isPasswordValid: true,
      isSubmitting:false,
      isSuccess: false,
      isFail: false
    );
  }
  factory LoginState.loading(){
    return LoginState(
      isNameValid: true,
      isPasswordValid: true,
      isSubmitting:true,
      isSuccess: false,
      isFail: false
    );
  }
  factory LoginState.fail(){
    return LoginState(
      isNameValid: true,
      isPasswordValid: true,
      isSubmitting:false,
      isSuccess: false,
      isFail: true
    );
  }
  factory LoginState.success(){
    return LoginState(
      isNameValid: true,
      isPasswordValid: true,
      isSubmitting:false,
      isSuccess: true,
      isFail: false
    );
  }


  LoginState update({
    bool isNamelValid,
    bool isPasswordValid,
  }) {
    return copyWith(
      isNamelValid: isNamelValid,
      isPasswordValid: isPasswordValid,
      isSubmitting: false,
      isSuccess: false,
      isFailure: false,
    );
  }

  LoginState copyWith({
    bool isNamelValid,
    bool isPasswordValid,
    bool isSubmitEnabled,
    bool isSubmitting,
    bool isSuccess,
    bool isFailure,
  }) {
    return LoginState(
      isNameValid: isNamelValid ?? this.isNameValid,
      isPasswordValid: isPasswordValid ?? this.isPasswordValid,
      isSubmitting: isSubmitting ?? this.isSubmitting,
      isSuccess: isSuccess ?? this.isSuccess,
      isFail: isFailure ?? this.isFail,
    );
  }




}