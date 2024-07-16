public class NBody {
    
    /**
     * 给定文件名，返回该文件中宇宙的半径。
     * 
     * @param fileName 文件名
     * @return 宇宙的半径
     */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt(); // 忽略第一行的行星数量
        double radius = in.readDouble();
        return radius;
    }

    /**
     * 给定文件名，返回该文件中所有行星的数组。
     * 
     * @param fileName 文件名
     * @return 行星数组
     */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int numPlanets = in.readInt();
        in.readDouble(); // 忽略宇宙的半径
        Planet[] planets = new Planet[numPlanets];

        for (int i = 0; i < numPlanets; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return planets;
    }

    
    public static void main(String[] args) {
        // 解析命令行参数
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        // 启用双缓冲技术以防止动画闪烁
        StdDraw.enableDoubleBuffering();

            // 播放音乐
        StdAudio.play("audio/2001.mid");

        // 创建时间变量并初始化为0
        double time = 0;
        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            // 计算每个行星的净x和y方向上的力
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            // 更新每个行星的位置、速度和加速度
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // 绘制背景图像
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // 绘制所有行星
            for (Planet planet : planets) {
                planet.draw();
            }

            // 显示离屏缓冲区
            StdDraw.show();

            // 暂停动画10毫秒
            StdDraw.pause(10);

            // 增加时间变量
            time += dt;
        }
    }

}
