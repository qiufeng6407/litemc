package pub.qiuf.litemc.app;

public class TestPostion {
    public static void main(String[] args) throws InterruptedException {
        final int moveSpeed = 50;

        int[] p = new int[] { -1203150, -2842750 };
        int[] d = new int[] { -1203000, -2843100 };

        while (!(p[0] == d[0]) || !(p[1] == d[1])) {
            Thread.sleep(50);
            int dx = d[0] - p[0];
            int dz = d[1] - p[1];
            int absDeltaX = 0;
            int absDeltaZ = 0;
            if (dz == 0) {
                absDeltaX = moveSpeed;
            } else if (dx == 0) {
                absDeltaZ = moveSpeed;
            } else {
                double angle = Math.atan(dx * 1.0 / dz);
                absDeltaX = (int) Math.abs((Math.cos(angle) * moveSpeed));
                absDeltaZ = (int) Math.abs(Math.sin(angle) * moveSpeed);
            }
            int deltaX = 0;
            int deltaZ = 0;
            if (absDeltaX >= Math.abs(dx)) {
                deltaX = dx;
            } else {
                deltaX = (dx) / Math.abs(dx) * absDeltaX;
            }
            if (absDeltaZ >= Math.abs(dz)) {
                deltaZ = dz;
            } else {
                deltaZ = (dz) / Math.abs(dz) * absDeltaZ;
            }
            p[0] += deltaX;
            p[1] += deltaZ;
            System.err.println(String.format("%d %d | %d %d -> %d %d | %d %d", dx, dz, deltaX, deltaZ, p[0], p[1], d[0], d[1]));
        }
    }
}
