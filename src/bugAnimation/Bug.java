package bugAnimation;
import java.util.*;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class Bug implements BugWorldObject, Food{
	private String species;
	private String name;
	private ImageView symbol;
	private double x, y, size, energy, speed;
	private int ID;
	protected World world;
	private boolean isAlive;
	private Circle color;
	private Set<String> foodType = new HashSet<>();
	private List<Food> allFood = new ArrayList<>();

	public Bug(String species, String name, ImageView symbol, double x, double y, double size, double energy,
			double speed, int iD, World world, boolean isAlive, Circle color) {
		super();
		this.species = species;
		this.name = name;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.size = size;
		this.energy = energy;
		this.speed = speed;
		ID = iD;
		this.world = world;
		this.isAlive = isAlive;
		this.color = color;
	}

	public Bug(String name, double x, double y, double energy, World world, boolean isAlive) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.energy = energy;
		this.world = world;
		this.isAlive = isAlive;
	}

	public String toText() {
		return "Bug [species=" + species + ", name=" + name + ", symbol=" + symbol + ", x=" + x + ", y=" + y
				+ ", energy=" + energy + ", ID=" + ID +"]";
	}

	@Override
	public String toString() {
		return "Bug [species=" + species + ", symbol=" + symbol + " ]";
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageView getSymbol() {
		return symbol;
	}

	public void setSymbol(ImageView symbol) {
		this.symbol = symbol;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Circle getCircle() {
		return color;
	}

	public void setCircle(Circle color) {
		this.color = color;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public void gainEnergy(double d) {
        this.energy += d;
    }

    public void loseEnergy(double energy) {
        this.energy -= energy;
    }

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	
	public double getArea() {
		return world.getWidth()*world.getHeight();
	}
	
	public boolean isHungry() {
		return this.getEnergy() < (energy*0.5);
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public Set<String> getFoodType() {
		return foodType;
	}

	public void setFoodType(Set<String> foodType) {
		this.foodType = foodType;
	}

	public List<Food> getAllFood() {
		return allFood;
	}

	public void setAllFood(List<Food> allFood) {
		this.allFood = allFood;
	}

	public void liveCircleWorld() {
		this.loseEnergy(1);
		int randomDirection = (int) ((Math.random() * 4) + 1);
		Food food = this.getClosestFoodItem();
		this.seekCircleFood(food);
		if(this.energy > 50) {
			move(randomDirection);
		}else{
			eatFood(food);
		}
	}
	public void liveImageWorld() {
		this.loseEnergy(1);
		int randomDirection = (int) ((Math.random() * 4) + 1);
		Food food = this.getClosestFoodItem();
		this.seekImageFood(food);
		if(this.energy > 50) {
			move(randomDirection);
		}else{
			eatFood(food);
		}
	}
	
	public void isEaten() {
		this.setEnergy(0);
		this.setAlive(false);
		this.world.getObjects().remove(this);
		this.world.getFood().remove(this);
		this.world.getBugs().remove(this);
	}
	
	public void idDead() {
		this.setAlive(false);
		this.world.getObjects().remove(this);
		this.world.getFood().remove(this);
		this.world.getBugs().remove(this);
	}
	
	public void seekCircleFood(Food food) {
		int foodDirection = 0;
		int randomDirection = (int) ((Math.random() * 4) + 1);
		boolean inRange = this.isCircleIntersect((BugWorldObject)food);
		while(!inRange) {
			foodDirection = this.getFoodDirection(food);
			move(foodDirection);
			if(isNextObstacle(foodDirection)) {
				move(randomDirection);
				seekCircleFood(food);
			}
		}
	}
	
	public void eatFood(Food f) {
		this.gainEnergy(f.getSize());
		f.isEaten();
	}
	
	public void seekImageFood(Food food) {
		int foodDirection = 0;
		int randomDirection = (int) ((Math.random() * 4) + 1);
		boolean inRange = this.isImageIntersect((BugWorldObject)food);
		while(!inRange) {
			foodDirection = this.getFoodDirection(food);
			move(foodDirection);
			if(isNextObstacle(foodDirection)) {
				move(randomDirection);
				seekImageFood(food);
			}
		}
	}
	
	public Food getClosestFoodItem() {
		allFood.removeAll(allFood);
		double d = Double.POSITIVE_INFINITY;
		int index = -1;
		for(Food f: world.getFood()){
			if(this.foodType.contains(f.getClass().toString())) {
				this.allFood.add(f);
			}
		}
		for (int i = 0; i < allFood.size(); i++) {
			Food f = allFood.get(i);
//			double gap = Math.sqrt(Math.pow(this.x - f.getX(), 2)
//					+ Math.pow(this.y - f.getY(), 2));
			double vx = this.x - f.getX(), vy = this.y - f.getY();
			double distance = StrictMath.sqrt(vx * vx + vy * vy);
			if (distance < d) {
				d = distance;
				index = i;
			}
		}
		if (index != -1) {
			return allFood.get(index);
		}
		return null;
	}

	public int getFoodDirection(Food f) {
		if(this.x == f.getX() && this.y != f.getY()) {
			if(f.getY()<this.y) {
				return 1;
			} else {
				return 2;
			}
		} else if(this.x != f.getX() && this.y == f.getY()) {
			if(f.getX() < this.x) {
				return 4;
			} else {
				return 3;
			}
		}
			return 0;
	}

	public boolean isNextObstacle(int direction) {
		double xMoved = 0, yMoved = 0;
		if (direction == 1) {
			xMoved = this.x;
			yMoved = this.y - this.speed;
		}if (direction == 2) {
			xMoved = this.x;
			yMoved = this.y + this.speed;
		}if (direction == 3) {
			xMoved = this.x + this.speed;
			yMoved = this.y;
		}if (direction == 4) {
			xMoved = this.x - this.speed;
			yMoved = this.y;
		}
		for (BugWorldObject obs : this.world.getObstacles()) {
			if (obs.getX() == xMoved && obs.getY() == yMoved) {
				return true;
			}
		}
		return false;

	}
	public boolean isNextFood(int direction) {
		double xMoved = 0, yMoved = 0;
		if (direction == 1) {
			xMoved = this.x;
			yMoved = this.y - 1;
		}if (direction == 2) {
			xMoved = this.x;
			yMoved = this.y + 1;
		}if (direction == 3) {
			xMoved = this.x + 1;
			yMoved = this.y;
		}if (direction == 4) {
			xMoved = this.x - 1;
			yMoved = this.y;
		}
		for (Food f : allFood) {
			if (f.getX() == xMoved && f.getY() == yMoved) {
				return true;
			}
		}
		return false;
		
	}
	
	public void move(int direction) {
		//1 north 2 south 3 east 4 west
		if (direction == 1 && this.y - 1 > this.size) {
			this.y = this.y - 1;
		} else if (direction == 2 && this.y + 1 <= this.world.getHeight() - this.size) {
			this.y = this.y + this.speed;
		} else if (direction == 3 && this.x + 1 <= this.world.getWidth() - this.size) {
			this.x = this.x + this.speed;
		} else if (direction == 4 && this.x - 1 > this.size){
			this.x = this.x - this.speed;
		}
	} 

	public boolean isCircleIntersect(BugWorldObject o) {
		return this.getCircle().getBoundsInParent().intersects(o.getCircle().getBoundsInParent());
	}
	
	public boolean isImageIntersect(BugWorldObject o) {
		return this.getSymbol().getBoundsInParent().intersects(o.getSymbol().getBoundsInParent());
	}


}
