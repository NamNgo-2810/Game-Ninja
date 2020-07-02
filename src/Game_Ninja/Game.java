package Game_Ninja;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Game_Ninja.Entity.*;
import Game_Ninja.Graphics.GUI.Launcher;
import Game_Ninja.Graphics.Sprite;
import Game_Ninja.input.KeyInput;
import Game_Ninja.input.MouseInput;

public class Game extends Canvas implements Runnable {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;
    private static final int SCALE = 4;
    private static final String TITLE = "NINJA";
    private boolean running = false;

    public static Launcher launcher;
    private static Camera camera;
    private Thread thread;
    private BufferedImage image;
    private BufferedImage background;
    public static boolean showInstruction;
    public static boolean showDeathScreen;
    public static boolean playing;
    public static Handler handler;
    public static Sprite land = new Sprite("res/Tiles/Land.png");
    public static Sprite rock = new Sprite("res/Tiles/rock.png");
    public static Sprite invisible_wall = new Sprite("res/Tiles/Invisible wall.png");
    public static Sprite coin = new Sprite("res/Tiles/Coin_1.png");
    public static Sprite lives = new Sprite("res/Tiles/Lives.png");

    public static final Sprite player_right = new Sprite("res/Ninja_right/ninja_stand_right.png");
    public static final Sprite player_jump_right = new Sprite("res/Ninja_right/ninja_jump_right_1.png");
    public static final Sprite player_left = new Sprite("res/Ninja_left/ninja_stand_left.png");
    public static final Sprite player_jump_left = new Sprite("res/Ninja_left/ninja_jump_left_1.png");
    /*Từng phần chuyển động được chia thành các mảng khác nhau để tiện render hoạt họa tại từng phương hướng*/
    public static Sprite[] player_move_right = new Sprite[6];
    public static Sprite[] player_move_left = new Sprite[6];
    public static Sprite[] player_attack_right = new Sprite[2];

    public static Sprite[] player_attack_left = new Sprite[2];
    public static Sprite[] enemy_move_right = new Sprite[3];
    public static Sprite[] enemy_move_left = new Sprite[3];
    public static Sprite[] enemy_attack_right = new Sprite[3];
    public static Sprite[] enemy_attack_left = new Sprite[3];


    private Game() {
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    private void init() { //Hàm khởi tạo các đối tượng trong chương trình
        handler = new Handler();
        camera = new Camera();
        launcher = new Launcher();
        MouseInput mouse = new MouseInput();
        addKeyListener(new KeyInput());
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        //Gọi các file ảnh của nhân vật trong game
        for (int i = 0; i < 6; i++){
            player_move_left[i] = new Sprite("res/Ninja_left/ninja_move_left_" + (i+1) + ".png");
            player_move_right[i] = new Sprite("res/Ninja_right/ninja_move_right_" + (i+1) + ".png");
        }
        player_attack_left[0] = new Sprite("res/Ninja_left/ninja_attack_left_1.png");
        player_attack_left[1] = new Sprite("res/Ninja_left/ninja_stand_left.png");

        player_attack_right[0] = new Sprite("res/Ninja_right/ninja_attack_right_1.png");
        player_attack_right[1] = new Sprite("res/Ninja_right/ninja_stand_right.png");

        enemy_attack_left[0] = new Sprite("res/Samurai_left/samurai_attack_left_1.png");
        enemy_attack_left[1] = new Sprite("res/Samurai_left/samurai_attack_left_2.png");
        enemy_attack_left[2] = new Sprite("res/Samurai_left/samurai_move_left_1.png");
        enemy_attack_right[0] = new Sprite("res/Samurai_right/samurai_attack_right_1.png");
        enemy_attack_right[1] = new Sprite("res/Samurai_right/samurai_attack_right_2.png");
        enemy_attack_right[2] = new Sprite("res/Samurai_right/samurai_move_right_1.png");
        try {
            image = ImageIO.read(new File("res/Level_2.png"));
            background = ImageIO.read(new File("res/Background3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            enemy_move_left[i] = new Sprite("res/Samurai_left/Samurai_move_left_" + (i+1) + ".png");
            enemy_move_right[i] = new Sprite("res/Samurai_right/Samurai_move_right_" + (i+1) + ".png");
        }

        handler.createLevel(image);
    }

    private synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this, "Thread");
        thread.start();
    }

    private synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() { //chạy và tính số khung hình/giây
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        int frames = 0;
        int ticks = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                ticks++;
                delta--;
            }
            try {
                render();
            } catch (IOException e) {
                e.printStackTrace();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(frames + "Frames Per Second / " + ticks + " Updates Per Second");
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }

    private void render() throws IOException { // Hàm chạy đồ họa
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(background, 0,0, getWidth(), getHeight(), null);
        g.setColor(Color.RED);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("Health: " + Player.getHealth(), 10, 30);
        g.drawString("Lives: " + Player.getLives(), 10, 50);
        g.setColor(Color.YELLOW);
        g.drawString("Score: " + Player.getScore(), 10, 70);

        if(playing && !showInstruction && !showDeathScreen) {
            g.translate(camera.getX(), camera.getY());
            handler.render(g);
        }
        else if(!playing && showInstruction && !showDeathScreen) {
            handler.showInstruction(g);
        }
        else if(showDeathScreen) {
            handler.showDeathScreen(g);
        }
        else launcher.render(g);
        g.dispose();
        bs.show();
    }

    private void tick() { //Hàm thực hiện hoạt động trong game
        if(playing) handler.tick();
        for (int i = 0; i < handler.entity.size();i++) {
            Entity e = handler.entity.get(i);
            if(e.get_id()==ID.player) {
                camera.tick(e);
            }
        }
    }

    public static int getFrameWidth() {
        return WIDTH*SCALE;
    }

    public static int getFrameHeight() {
        return HEIGHT*SCALE;
    }
    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
    }
}