public class Planet {
    public double xxPos; // 当前的x位置
    public double yyPos; // 当前的y位置
    public double xxVel; // 当前在x方向上的速度
    public double yyVel; // 当前在y方向上的速度
    public double mass; // 质量
    public String imgFileName; // 对应行星图像的文件名

    /**
     * 构造一个新的Planet对象。
     * 
     * @param xP 行星的x位置。
     * @param yP 行星的y位置。
     * @param xV 行星在x方向上的速度。
     * @param yV 行星在y方向上的速度。
     * @param m 行星的质量。
     * @param img 描述行星的图像文件名。
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * 构造一个新的Planet对象，它是给定Planet对象的副本。
     * 
     * @param p 要复制的Planet对象。
     */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

/**
     * 计算两个行星之间的距离。
     * 
     * @param p 另一个行星对象
     * @return 两个行星之间的距离
     */
    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos; // 计算x轴方向上的距离
        double dy = this.yyPos - p.yyPos; // 计算y轴方向上的距离
        return Math.sqrt(dx * dx + dy * dy); // 使用勾股定理计算距离
    }

    /**
     * 计算由另一个行星对当前行星施加的引力。
     * 
     * @param p 另一个行星对象
     * @return 施加在当前行星上的引力
     */
    public double calcForceExertedBy(Planet p) {
        final double G = 6.67e-11; // 万有引力常数
        double distance = this.calcDistance(p); // 计算两个行星之间的距离
        return (G * this.mass * p.mass) / (distance * distance); // 使用万有引力公式计算力
    }

    /**
     * 计算在x轴方向上，由另一个行星对当前行星施加的引力分量。
     * 
     * @param p 另一个行星对象
     * @return x轴方向上的引力分量
     */
    public double calcForceExertedByX(Planet p) {
        double force = this.calcForceExertedBy(p); // 计算总引力
        double distance = this.calcDistance(p); // 计算两个行星之间的距离
        double dx = p.xxPos - this.xxPos; // x轴方向上的距离
        return (force * dx) / distance; // 计算x轴方向上的引力分量
    }

    /**
     * 计算在y轴方向上，由另一个行星对当前行星施加的引力分量。
     * 
     * @param p 另一个行星对象
     * @return y轴方向上的引力分量
     */
    public double calcForceExertedByY(Planet p) {
        double force = this.calcForceExertedBy(p); // 计算总引力
        double distance = this.calcDistance(p); // 计算两个行星之间的距离
        double dy = p.yyPos - this.yyPos; // y轴方向上的距离
        return (force * dy) / distance; // 计算y轴方向上的引力分量
    }

    /**
     * 计算所有其他行星对当前行星在x轴方向上施加的净引力。
     * 
     * @param planets 行星数组
     * @return x轴方向上的净引力
     */
    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0; // 初始化x轴方向上的净引力为0
        for (Planet p : planets) {
            if (!this.equals(p)) { // 排除对自身施加的引力
                netForceX += this.calcForceExertedByX(p); // 累加x轴方向上的引力分量
            }
        }
        return netForceX;
    }

    /**
     * 计算所有其他行星对当前行星在y轴方向上施加的净引力。
     * 
     * @param planets 行星数组
     * @return y轴方向上的净引力
     */
    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0; // 初始化y轴方向上的净引力为0
        for (Planet p : planets) {
            if (!this.equals(p)) { // 排除对自身施加的引力
                netForceY += this.calcForceExertedByY(p); // 累加y轴方向上的引力分量
            }
        }
        return netForceY;
    }


    /**
     * 根据施加的x轴和y轴上的力，以及时间间隔，更新行星的速度和位置。
     * 
     * @param dt 时间间隔
     * @param fX x轴上的力
     * @param fY y轴上的力
     */
    public void update(double dt, double fX, double fY) {
        // 计算x轴和y轴上的加速度
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        // 更新速度
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;

        // 更新位置
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}