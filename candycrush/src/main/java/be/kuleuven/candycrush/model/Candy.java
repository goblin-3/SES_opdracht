package be.kuleuven.candycrush.model;

public interface Candy {

    record NormalCandy(int color) implements Candy {
        public NormalCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }
    }

    record CaveCandy(int color) implements Candy {
        public CaveCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }
    }

    record ExplosiveCandy(int color) implements Candy {
        public ExplosiveCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }
    }

    record ChocolatCandy(int color) implements Candy {
        public ChocolatCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }
    }

    record NuclearCandy(int color) implements Candy {
        public NuclearCandy {
            if (color < 0 || color > 3) {
                throw new IllegalArgumentException("Color value must be between 0 and 3");
            }
        }
    }
}
