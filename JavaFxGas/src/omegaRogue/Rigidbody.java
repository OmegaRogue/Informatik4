package omegaRogue;

public interface Rigidbody {
    boolean collideWith(Rigidbody r);
    EnumDirection collideAt(Rigidbody r);
}
