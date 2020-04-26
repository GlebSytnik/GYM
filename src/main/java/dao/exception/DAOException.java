package dao.exception;



    public class DAOException extends RuntimeException {
        /**
         * Constructs a new exception with {@code null} as its detail message.
         */
        public DAOException() {
            super();
        }

        /**
         * Constructs a new exception with the specified detail message.
         *
         * @param   message   the detail message.
         */
        public DAOException(String message) {
            super(message);
        }

        /**
         * Constructs a new exception with the specified detail message and
         * cause.
         *
         * @param  message the detail message.
         * @param  cause the cause.
         */
        public DAOException(String message, Throwable cause) {
            super(message, cause);
        }
    }

