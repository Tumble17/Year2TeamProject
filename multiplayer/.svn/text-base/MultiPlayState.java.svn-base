package multiplayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import menus.MultiMenu;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import client.Client;

import character.AIEnemy;
import character.User;

/**
 * 
 * @author Chau Nguyen 1254579
 * @author Jack Dyer 1223659
 *
 */
public class MultiPlayState {

	public static int x = 200;
	public static int y = 100;
	public static int userWidth = 51;
	public static int userHeight = 64;
	public static boolean userIsActive = true;

	// public static Client userCl;
	static String cords = Client.cords;

	static AIEnemy enemy;
	static User user;
	public static int enemyWidth = 51;
	public static int enemyHeight = 64;
	public static int enPerWave = 4;
	static boolean collision;
	static int totalEnemies = 0;
	static int totalKills = 0;
	static int waveNo = 0;
	public static boolean startEnemyPaint = false;

	// -----------------------------------
	// USER IMAGES FIELDS
	// ANIMATION HOLDERS TO DEDUCE ANIMATION SEQUENCES
	// -----------------------------------
	static Animation p1currentAni;
	static Animation p1previousAni; // USED TO DECIDE WHICH ANIMATION TO USE IN
									// AMBIGUOUS CASE
	static Animation p2currentAni;
	static Animation p2previousAni;
	static Animation p3currentAni;
	static Animation p3previousAni;
	static Animation p4currentAni;
	static Animation p4previousAni;

	// -----------------------------------
	// PLAYER 1 (SELF) ANIMATIONS
	// -----------------------------------
	static boolean facingLeft = false;
	static SpriteSheet player1StandR;
	public static Animation p1StandRight;
	static SpriteSheet player1StandL;
	public static Animation p1StandLeft;
	static SpriteSheet player1Right;
	public static Animation p1Right;
	static SpriteSheet player1Left;
	public static Animation p1Left;
	static SpriteSheet player1LAttR;
	public static Animation p1LAttRight;
	static SpriteSheet player1LAttL;
	public static Animation p1LAttLeft;
	static SpriteSheet player1SAttR;
	public static Animation p1SAttRight;
	static SpriteSheet player1SAttL;
	public static Animation p1SAttLeft;

	// -----------Weapons-----------------
	// -----------------------------------
	// WOODEN SWORD ANIMATIONS
	// -----------------------------------
	static SpriteSheet woodenswordStandR;
	public static Animation wsStandRight;
	static SpriteSheet woodenswordStandL;
	public static Animation wsStandLeft;
	static SpriteSheet woodenswordLAttR;
	public static Animation wsLAttRight;
	static SpriteSheet woodenswordLAttL;
	public static Animation wsLAttLeft;
	static SpriteSheet woodenswordSAttR;
	public static Animation wsSAttRight;
	static SpriteSheet woodenswordSAttL;
	public static Animation wsSAttLeft;

	// CURRENT ANIMATION OF THE WEAPON THE USER SEES ON SCREEN
	public static Animation wpnCurrentAni;

	// -----------------------------------
	// ENEMY IMAGES FIELDS
	// -----------------------------------
	static Animation curAni = AIEnemy.curAni;
	static SpriteSheet enemyLSheet;
	public static Animation enemyLeft;
	static SpriteSheet enemyLHitSh;
	static Animation enemyLHit;
	static SpriteSheet enemyLAttack;
	static Animation enemyLAtt;
	static SpriteSheet enemyRSheet;
	public static Animation enemyRight;
	static SpriteSheet enemyRHitSh;
	static Animation enemyRHit;
	static SpriteSheet enemyRAttack;
	static Animation enemyRAtt;
	static SpriteSheet enemyDeadSheet;
	static Animation enemyDead;

	// -----------------------------------
	// BOSS IMAGES FIELDS
	// -----------------------------------
	// GAZETI (MAGIC / IMMOBILE LR)
	/**
	 * SPRITESHEET USED: SOURCE AVAILABLE @
	 * http://sprites.technoized.com/images/sprite/ro/gazeti.png
	 */
	public static Animation gazCurAni;
	static SpriteSheet gazLeftS;
	public static Animation gazLeft;
	static SpriteSheet gazAttS;
	public static Animation gazAtt;
	static SpriteSheet gazDeadS;
	public static Animation gazDead;
	static SpriteSheet hyperBeam;
	public static Animation beam;
	public boolean isBossDeath;

	// GHOST (MAGIC / MOBILE)
	/**
	 * SPRITESHEET USED: SOURCE AVAILABLE @
	 * http://sprites.technoized.com/images/sprite/ro/wind_ghost.png
	 */
	static SpriteSheet ghostStandLeftS;
	public static Animation ghostStandLeft;
	static SpriteSheet ghostStandRightS;
	public static Animation ghostStandRight;
	static SpriteSheet ghostLeftS;
	public static Animation ghostLeft;
	static SpriteSheet ghostRightS;
	public static Animation ghostRight;
	static SpriteSheet ghostAttLeftS;
	public static Animation ghostAttLeft;
	static SpriteSheet ghostAttRightS;
	public static Animation ghostAttRight;
	static SpriteSheet ghostDeathS;
	public static Animation ghostDeath;
	static SpriteSheet lightningSheet;
	public static Animation lightning;

	// THANATOS (MELEE / MOBILE)
	/**
	 * SPRITESHEET USED: SOURCE AVAILABLE @
	 * http://sprites.technoized.com/images/sprite/ro/thanatos.png
	 */
	static SpriteSheet thanatosLeftS;
	public static Animation thanatosLeft;
	static SpriteSheet thanatosRightS;
	public static Animation thanatosRight;
	static SpriteSheet thanatosAttLeftS;
	public static Animation thanatosAttLeft;
	static SpriteSheet thanatosAttRightS;
	public static Animation thanatosAttRight;
	static SpriteSheet thanatosDeathS;
	public static Animation thanatosDeath;

	// -----------------------------------
	// CHARACTER ARRAYS
	// -----------------------------------
	public static LinkedList<User> arrOfUser = new LinkedList<User>();
	public static LinkedList<AIEnemy> arrOfEnemy = new LinkedList<AIEnemy>();
	public static List<AIEnemy> enemyToDie = new ArrayList<AIEnemy>();

	// -----------------------------------
	// GAME PARAMETERS FIELDS
	// -----------------------------------
	public static int bgWidth = 1260;
	public static int bgHeight = 680;
	static int gameAreaWidth = 1060;
	static int gameAreaHeight = 480;
	public static int gameAreaXStart = 200;
	public static int gameAreaYStart = 100;

	// -----------------------------------
	// GAME IMAGES
	// -----------------------------------
	static Image background;
	static Image foreground;
	static Image topground;
	// Health Bars
	static Image health;
	static Image health20;
	static Image health40;
	static Image health60;
	static Image health80;

	// User health
	static Image userHP10;
	static Image userHP20;
	static Image userHP30;
	static Image userHP40;
	static Image userHP50;
	static Image userHP60;
	static Image userHP70;
	static Image userHP80;
	static Image userHP90;
	static Image userHP;

	// Sidebar Images
	static Image leftBarBack;
	static SpriteSheet coinSS;
	static Animation coinSpin;
	static Image cpuLabel;
	static Image userLabel;
	static Image lightAttack;
	static Image strongAttack;
	static Image attackTimerGrad;

	// Tips Images
	static SpriteSheet tipsSpriteSheet;
	static Animation tips;

	// FIELD FOR USER PLAYER ATT DELAYS
	public static int attackDelay = 1000;
	public static int userTimer = 0;
	public static int userSTimer = 0;

	public static boolean userIsAttacking = false;

	// FIELD FOR USER'S ATTACK RANGE
	public static float atkRange;
	public static float wpnWidth = 60;

	static TimerTask timerTask;
	static Timer timer;

	/**
	 * Takes the coordinates of an enemy and user and checks for a collision
	 * 
	 * @param xVal
	 *            the users referenced x coordinate
	 * @param yVal
	 *            the users referenced y coordinate
	 * @param enXVal
	 *            the enemy x coordinate
	 * @param enYVal
	 *            the enemy y coordinate
	 * @return boolean of whether a collision has occurred
	 */
	public static boolean checkCollision(float xVal, float yVal, float enXVal,
			float enYVal) {

		if (xVal + userWidth >= enXVal && xVal <= enXVal + enemyWidth
				&& yVal + userHeight >= enYVal && yVal <= enYVal + enemyHeight) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Changes the userIsAttacking for half a second for animations
	 * 
	 * @param xVal
	 *            the x coordinate of the user attacking position
	 * @param yVal
	 *            the y coordinate of the user attacking position
	 */
	public static void userAttack(final int us) {

		Thread t = new Thread() {
			public void run() {

				arrOfUser.get(us).isAttacking = true;

				try {
					// swordSound();
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}

				arrOfUser.get(us).isAttacking = false;
			}

		};
		t.start();
	}

	public static void init(GameContainer gc) throws SlickException {

		// ------------------------------
		// REQUEST NEW INFO THREAD ( BACKGROUND)
		// ------------------------------
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				// TIMED ENEMY MOVEMENTS
				try {
					MultiPlayer.userCl.requestEnemy();
					
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}, 1, 8, TimeUnit.MILLISECONDS);
		ScheduledExecutorService hps = Executors.newScheduledThreadPool(1);
		hps.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				// TIMED ENEMY MOVEMENTS
					ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
					service.scheduleAtFixedRate(new Runnable() {

						@Override
						public void run() {
							// TIMED ENEMY MOVEMENTS
							try {
								MultiPlayer.userCl.requestUsersHP();
								
							} catch (IOException e) {
								e.printStackTrace();
							}

						}
					}, 1, 200, TimeUnit.MILLISECONDS);
					
				

			}
		}, 1, 8, TimeUnit.MILLISECONDS);

		// CREATES NEW USER (TEST)

		for (int b = 0; b < 4; b++) {
			user = new User(); // SPAWNS NEW
			user.spawn(); // ADDS USER TO ACTIVE USER LIST
			if (b < 2){
				user.isPainted = true;
			}
			arrOfUser.add(user);
		}
		

		// -----------------------------------------
		// CREATES NEW ENEMY (TEST)
		waveNo++;
		for (int z = 0; z < enPerWave; z++) {
			enemy = new AIEnemy(100);
			enemy.isActive = true;
			// SPAWNS NEW ENEMY (TEST)
			enemy.spawn("SSG");
			// ADDS ENEMY TO ACTIVE ENEMY LIST
			arrOfEnemy.add(enemy);

		}
		totalEnemies += enPerWave;
		// -----------------------------------------
		// -----------------------------------
		// INIT IMAGES
		// -----------------------------------
		// Images for the "top ground", background and foreground.   
		background = new Image("sprites/background.png");
		foreground = new Image("sprites/foreground.png");
		topground = new Image("sprites/topGround.png");
		
		// Images for the enemy's health bar.
		health = new Image("sprites/healthBar.png");
		health20 = new Image("sprites/healthBar20.png");
		health40 = new Image("sprites/healthBar40.png");
		health60 = new Image("sprites/healthBar60.png");
		health80 = new Image("sprites/healthBar80.png");
		
		// Images for the user's health bar.
		userHP = new Image("sprites/userHP.png");
		userHP10 = new Image("sprites/userHP10.png");
		userHP20 = new Image("sprites/userHP20.png");
		userHP30 = new Image("sprites/userHP30.png");
		userHP40 = new Image("sprites/userHP40.png");
		userHP50 = new Image("sprites/userHP50.png");
		userHP60 = new Image("sprites/userHP60.png");
		userHP70 = new Image("sprites/userHP70.png");
		userHP80 = new Image("sprites/userHP80.png");
		userHP90 = new Image("sprites/userHP90.png");

		// Images for the left side bar.
		leftBarBack = new Image("sprites/leftBarBack.png");
		coinSS = new SpriteSheet("sprites/coinSpin.png", 80, 80);
		coinSpin = new Animation(coinSS, 200);
		cpuLabel = new Image("sprites/cpuLabel.png");
		userLabel = new Image("sprites/userLabel.png");
		lightAttack = new Image("sprites/lightAttack.png");
		strongAttack = new Image("sprites/strongAttack.png");
		attackTimerGrad = new Image("sprites/attackTimersGradient.png");

		tipsSpriteSheet = new SpriteSheet("sprites/tips/tips2.png", 200, 250);
		tips = new Animation(tipsSpriteSheet, 3000);

		// -----------------------------------
		// PLAYER 1 DECLARATION (P1)
		// Right facing animations
		// -----------------------------------
		player1Right = new SpriteSheet("sprites/p1Right.png", 51, 53);
		p1Right = new Animation(player1Right, 200);
		player1Left = new SpriteSheet("sprites/p1Left.png", 51, 53);
		p1Left = new Animation(player1Left, 200);
		player1StandR = new SpriteSheet("sprites/p1StandRight.png", 51, 53);
		p1StandRight = new Animation(player1StandR, 200);
		player1StandL = new SpriteSheet("sprites/p1StandLeft.png", 51, 53);
		p1StandLeft = new Animation(player1StandL, 200);
		player1LAttR = new SpriteSheet("sprites/p1LAttRight.png", 40, 54);
		p1LAttRight = new Animation(player1LAttR, 200);
		player1LAttL = new SpriteSheet("sprites/p1LAttLeft.png", 40, 54);
		p1LAttLeft = new Animation(player1LAttL, 200);
		player1SAttR = new SpriteSheet("sprites/p1SAttRight.png", 53, 56);
		p1SAttRight = new Animation(player1SAttR, 300);
		player1SAttL = new SpriteSheet("sprites/p1SAttRight.png", 53, 56);
		p1SAttLeft = new Animation(player1SAttL, 300);
		// P1 CUR ANIMATION - Set to standing right facing at spawn
		p1currentAni = p1StandRight;

		// -----------------------------------
		// WEAPON DECLARATION FOR ALL WEAPONS
		// -----------------------------------
		woodenswordStandR = new SpriteSheet(
				"sprites/woodensword/WoodenSword Stand.png", 51, 53);
		wsStandRight = new Animation(woodenswordStandR, 200);
		woodenswordStandL = new SpriteSheet(
				"sprites/woodensword/WoodenSword StandLeft.png", 51, 53);
		wsStandLeft = new Animation(woodenswordStandL, 200);
		woodenswordLAttR = new SpriteSheet(
				"sprites/woodensword/WoodenSword LAttRight.png", 96, 54);
		wsLAttRight = new Animation(woodenswordLAttR, 200);
		woodenswordLAttL = new SpriteSheet(
				"sprites/woodensword/WoodenSword LAttLeft.png", 96, 54);
		wsLAttLeft = new Animation(woodenswordLAttL, 200);
		woodenswordSAttR = new SpriteSheet(
				"sprites/woodensword/WoodenSword SAttRight.png", 51, 55);
		wsSAttRight = new Animation(woodenswordSAttR, 200);
		woodenswordSAttL = new SpriteSheet(
				"sprites/woodensword/WoodenSword SAttLeft.png", 51, 55);
		wsSAttLeft = new Animation(woodenswordSAttL, 200);

		// WEAPON CUR ANIMATION - Set to standing right at spawn
		wpnCurrentAni = wsStandRight;

		// -----------------------------------

		// -----------------------------------
		// ENEMY IMAGES
		// -----------------------------------
		enemyLSheet = new SpriteSheet("/sprites/enemyLeft.png", 56, 58);
		enemyLeft = new Animation(enemyLSheet, 200);
		enemyLHitSh = new SpriteSheet("/sprites/enemyLHit.png", 64, 63);
		enemyLHit = new Animation(enemyLHitSh, 200);
		enemyRSheet = new SpriteSheet("/sprites/enemyRight.png", 56, 58);
		enemyRight = new Animation(enemyRSheet, 200);

		enemyDeadSheet = new SpriteSheet("/sprites/enemyDead.png", 56, 72);
		enemyDead = new Animation(enemyDeadSheet, 200);

		// -----------------------------------
	}

	public static void update(GameContainer gc, int i) throws SlickException,
			IOException {

		// ----------------
		// USER INPUTS
		// ----------------
		if (facingLeft) {
			p1currentAni = p1StandLeft;
			wpnCurrentAni = wsStandLeft;
		} else {
			p1currentAni = p1StandRight; // Defaults to standing right if no
											// input
											// is processed
			wpnCurrentAni = wsStandRight;
		}
		// ----------------
		// PROCESS INPUTS
		// ----------------
		Input input = gc.getInput();
		// RIGHT
		if ((input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
				&& x < (bgWidth - userWidth) && userIsActive) {
			x++;
			MultiPlayer.userCl.sendData(x, y);
			p1currentAni = p1Right;
			wpnCurrentAni = wsStandRight;
			facingLeft = false;
		}
		// LEFT
		if ((input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
				&& x > 200  && userIsActive) {
			x--;
			MultiPlayer.userCl.sendData(x, y);
			p1currentAni = p1Left;
			wpnCurrentAni = wsStandLeft;
			facingLeft = true;
		}
		// DOWN
		if ((input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
				&& y < (bgHeight - 100) - userHeight && userIsActive) {
			y++;
			MultiPlayer.userCl.sendData(x, y);
			if (facingLeft) {
				p1currentAni = p1Left;
				wpnCurrentAni = wsStandLeft;
			} else {
				p1currentAni = p1Right;
				wpnCurrentAni = wsStandRight;
			}
		}
		// UP
		if ((input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
				&& y > 100 && userIsActive) {
			y--;
			MultiPlayer.userCl.sendData(x, y);
			if (facingLeft) {
				p1currentAni = p1Left;
				wpnCurrentAni = wsStandLeft;
			} else {
				p1currentAni = p1Right;
				wpnCurrentAni = wsStandRight;
			}
		}

		// K OR SPACE (LIGHT ATTACK) ON ATTACK TIMER
		if (userTimer > 0) {
			userTimer -= i;

		} else if (userSTimer > 0) {
			userSTimer -= i;
		} else if ((input.isKeyPressed(Input.KEY_Z)
				|| input.isKeyPressed(Input.KEY_L)) && userIsActive) {

			// userAttack(x, y);

			// Checks if the user is facing left or right to set their attack
			// range.
			if (facingLeft) {
				atkRange = x - wpnWidth;
			} else if (!facingLeft) {
				atkRange = x + wpnWidth;
			}

			for (AIEnemy en : arrOfEnemy) {
				// CHECKS FOR COLLISION
				if (checkCollision(x, y, en.xVal, en.yVal) && userTimer <= 0) {
					AIEnemy.curAni = enemyLHit;
					int enNum = arrOfEnemy.indexOf(en);
					collision = true;
					MultiPlayer.userCl.sendAttack(enNum, Client.username);

					// CHECK DEATH
					if (en.hitPoints <= 0 && en.isActive == true) {

						totalKills++;
					}
				}

			}
			userTimer = attackDelay;
		} else if ((input.isKeyPressed(Input.KEY_K)
				|| input.isKeyPressed(Input.KEY_SPACE)) && userIsActive) {

			// userAttack(x, y);

			// Checks if the user is facing left or right to set their attack
			// range.
			if (facingLeft) {
				atkRange = x - wpnWidth + 20;
			} else if (!facingLeft) {
				atkRange = x + wpnWidth - 20;
			}

			for (AIEnemy en : arrOfEnemy) {

				// CHECKS FOR COLLISION
				if (checkCollision(x, y, en.xVal, en.yVal) && userTimer <= 0) {
					if (facingLeft) {
						AIEnemy.spCurAni = enemyLHit;
					} else if (!facingLeft) {
						AIEnemy.spCurAni = enemyRHit;
					}

					int enNum = arrOfEnemy.indexOf(en);
					collision = true;
					MultiPlayer.userCl.sendAttack(enNum, Client.username);

					// CHECK DEATH
					if (en.hitPoints <= 0 && en.isActive == true) {
						// en.die(en);

						// UPDATE STATISTICS
						totalKills++;
					}
					// RESET TIMER

				}

			}
			userSTimer = attackDelay + 1000;

		}

		// Key input to make the user go to the Game Over screen.
		if (input.isKeyPressed(Input.KEY_R)) {
			MultiPlayer.isGameOver = true;
			
		}

		// Key input to make the user exit the game.
		if (input.isKeyPressed(Input.KEY_U)) {
			try {
				MultiPlayer.userCl.sendClose();
				MultiMenu.appgc.exit();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

	// ----------------

	public static void render(GameContainer gc, Graphics g)
			throws SlickException {
		// ------------------------------
		// BASIC DISPLAYS
		// ------------------------------

		// ------------------------------
		// BACKGROUND
		// ------------------------------
		background.draw(gameAreaXStart, gameAreaYStart, gameAreaWidth,
				gameAreaHeight);
		// ------------------------------
		// USER AREA
		// ------------------------------

		float stringMargin = 10;
		float userBoxHeight = 40;
		leftBarBack.draw(0, 0);

		// DISPLAYS HEALTH ACCORDING TO VALUE
		// ------------------------------
		// IF ALIVE ENEMY RENDER
		// ------------------------------
		
		for (int y = 0; y < arrOfUser.size(); y++) {
			if(arrOfUser.get(y).isPainted == true){
			userLabel.draw(5, (y * userBoxHeight) + 240);
			if (arrOfUser.get(y).hitPoints > 0) {
				health.draw(stringMargin + 80, (y * userBoxHeight) + 240, 100,
						40);
				userHP.draw(stringMargin + 80, (y * userBoxHeight) + 240,
						arrOfUser.get(y).hitPoints, 40);
			}
			}
		}

		coinSpin.draw(stringMargin, userBoxHeight);
		// DISPLAY POINTS AND ATTACK TIMER
		lightAttack.draw(stringMargin, userBoxHeight + 100);
		strongAttack.draw(stringMargin + 95, userBoxHeight + 100);
		// DISPLAYS THE TIMER FOR LIGHT ATTACK
		attackTimerGrad.draw(stringMargin, userBoxHeight + 185, 85,
				-(((float) userTimer / (float) attackDelay) * 85));
		// DISPLAYS THE TIMER FOR STRONG ATTACK
		attackTimerGrad.draw(stringMargin + 95, userBoxHeight + 185, 85,
				-(((float) userSTimer / 2000) * 85));

		// TIPS DISPLAY TICKER
		tips.draw(5, 420, 190, 255);

		// ------------------------------
		// IF ALIVE ENEMY RENDER
		// ------------------------------
		for (AIEnemy en : arrOfEnemy) {
			if (en.isActive && startEnemyPaint) {
				if (en.isAttacking){
					AIEnemy.curAni = enemyLHit;
				} else {
				AIEnemy.curAni = enemyLeft;
				}
				AIEnemy.curAni.draw(en.xVal, en.yVal);
				health.draw(en.xVal - (float) 7,
						en.yVal - 13, ((float)en.hitPoints /  (float) 100) * (float) 60, 10);
				
			}

		}
		// ------------------------------
		// BOSS RENDER
		// ------------------------------

		// ------------------------------
		// IF ALIVE USER RENDER
		// ------------------------------
		for (User us : arrOfUser) {
			
			if (us.isAttacking) {
				if (userSTimer > 0) {
					if (facingLeft) {
						g.drawAnimation(p1SAttLeft, x, y);
						g.drawAnimation(wsSAttLeft, x - 30, y);
					}
					if (!facingLeft) {
						g.drawAnimation(p1SAttRight, x, y);
						g.drawAnimation(wsSAttRight, x, y);
					}
				} else {
					if (facingLeft) {
						g.drawAnimation(p1LAttLeft, x, y);
						g.drawAnimation(wsLAttLeft, x - 30, y);
					}
					if (!facingLeft) {
						g.drawAnimation(p1LAttRight, x, y);
						g.drawAnimation(wsLAttRight, x, y);
					}
				}

			} else if(us.isPainted) {
				g.drawAnimation(p1currentAni, us.xVal, us.yVal);
				g.drawAnimation(wpnCurrentAni, us.xVal, us.yVal);
			} 

		}

		foreground.draw(gameAreaXStart, (gameAreaYStart + gameAreaHeight) - 60);
		topground.draw(gameAreaXStart, 0, 1100, 100);
	}

	public static void create() throws SlickException {
		// ------------------------------
		// BACKGROUND THREAD
		// ------------------------------
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				// // ------------------------------
				// // REMOVE INACTIVE ENEMIES
				// // ------------------------------
				// arrOfEnemy.removeAll(enemyToDie);
				// enemyToDie.clear();

			}
		}, 1, 1, TimeUnit.SECONDS);
	}

}
