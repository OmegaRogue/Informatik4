package omegaRogue;

import java.util.Vector;

public class Vector3 extends Vector {
    double x = 0;
    double y = 0;
    double z = 0;
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    public Vector3(double x) {
        this.x = x;
        this.y = 0;
        this.z = 0;
    }
    public void translate(Vector3 vec3) {
        this.x += vec3.x;
        this.y += vec3.y;
        this.z += vec3.z;
    }
    public void normalize() {
        double length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
    }
    public Vector3 multiply(double n)
    {
        return new Vector3(this.x*n,this.y*n,this.z*n);
    }
    public static Vector3 Zero() {
        return new Vector3(0,0,0);
    }
    public double length() {
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
    }
    public void reverse(boolean rx, boolean ry, boolean rz) {
        if(rx) x = -x;
        if(ry) y = -y;
        if(rz) z = -z;
    }
    public void reverse(boolean rx, boolean ry) {
        if(rx) x = -x;
        if(ry) y = -y;
    }
    public void reverseX() {
        x = -x;
    }
    public void reverseY() {
        y = -y;
    }
    public void reverseZ() {
        z = -z;
    }

    public double distanceTo(Vector3 vector) {
        double dx = this.x -= vector.x;
        double dy = this.y -= vector.y;
        return Math.sqrt((dx*dx)+(dy*dy));
    }
}
