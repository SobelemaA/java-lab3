package app.model;

public enum ProductType {
    FOOD {
        @Override
        public String toString() {
            return "Food";
        }
    },
    BEER {
        @Override
        public String toString() {
            return "Beer";
        }
    },
    WINE {
        @Override
        public String toString() {
            return "Wine";
        }
    },
    CIGARETTE {
        @Override
        public String toString() {
            return "Cigarette";
        }
    }
}
