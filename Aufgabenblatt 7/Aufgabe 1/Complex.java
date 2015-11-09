/* Die Komplexen Zahlen a+b i, mit (a+bi)+(c+di) = (a+c)+(b+d)i und (a+bi)(c+di) = (ac-bd)+(ad+bc)i */
class Complex {
  double a;
  double b;

  /* Der Konstruktor */
  public Complex(double a, double b) {
    this.a = a;
    this.b = b;
  }

  /* Zugriff auf den realen Anteil */
  double real() {
    return a;
  }

  /* Zugriff auf den imaginaeren Anteil */
  double imag() {
    return b;
  }

  /* Multiplikation ohne Veraenderung der beteiligten Objekte */
  Complex mult(Complex o) {
    return new Complex(real()*o.real()-imag()*o.imag(),real()*o.imag()+imag()*o.real());
  }

  /* Multiplikation mit Veraenderung der beteiligten Objekte. Gibt this zurueck */
  Complex mult_inplace(Complex o) {
    a = real()*o.real()-imag()*o.imag();
    b = real()*o.imag()+imag()*o.real();
    return this;
  }

  /* Das Quadrat (c^2 = c*c) ohne Veraenderung der beteiligten Objekte */
  Complex sqr() {
    return mult(this);
  }

  /* Das Quadrat (c^2 = c*c) in place. Veraendert das Objekt und gibt this zurueck. */
  Complex sqr_inplace() {
    return mult_inplace(this);
  }

  /* Das Quadrat des Betrags */
  double abs_sqr() {
    return real()*real()+imag()*imag();
  }

  /* Addition zweier komplexer Zahlen ohne Veraenderung der beteiligten Objekte */
  public Complex add(Complex c) {
    return new Complex(real()+c.real(), imag()+c.imag());
  }

  /* Addition zweier komplexer Zahlen mit Veraenderung der beteiligten Objekte */
  public Complex add_inplace(Complex c) {
    a = real()+c.real();
    b = imag()+c.imag();
    return this;
  }
}
