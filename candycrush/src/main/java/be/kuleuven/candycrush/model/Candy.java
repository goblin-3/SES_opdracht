package be.kuleuven.candycrush.model;
public interface Candy {

    int getColor();

    record NormalCandy(int color) implements Candy {
        public NormalCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }

        @Override
        public int getColor() {
            return color;
        }
    }

    record CaveCandy(int color) implements Candy {
        public CaveCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }

        @Override
        public int getColor() {
            return color;
        }
    }

    record ExplosiveCandy(int color) implements Candy {
        public ExplosiveCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }

        @Override
        public int getColor() {
            return color;
        }
    }

    record ChocolatCandy(int color) implements Candy {
        public ChocolatCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }

        @Override
        public int getColor() {
            return color;
        }
    }

    record NuclearCandy(int color) implements Candy {
        public NuclearCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }

        @Override
        public int getColor() {
            return color;
        }
    }
}
