class Validators {
  static final RegExp _nameRegExp = RegExp(
    r'^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)[0-9a-zA-Z_]{6,16}$',
  );
  static final RegExp _passwordRegExp = RegExp(
    r'^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{8,16}$',
  );

  static isValidName(String name) {
    return _nameRegExp.hasMatch(name);
  }

  static isValidPassword(String password) {
    return _passwordRegExp.hasMatch(password);
  }
}
