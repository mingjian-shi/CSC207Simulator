package com.example.csc207simulator.AccountManagement;

import com.example.csc207simulator.Player;
import com.example.csc207simulator.SetupManagement.Setup;

/**
 * This class Account is responsible for storing all the account related information.
 */
public class Account {

    private String userName;
    private String passWord;
    private Player player;
    private int currentState;
    private Setup setUp;

    /**
     * constructs an account
     * @param builder accountbuilder.
     */
    public Account(AccountBuilder builder) {
        this.userName = builder.userName;
        this.passWord = builder.passWord;
        this.player = builder.player;
        this.currentState = builder.currentState;
        this.setUp = builder.setUp;
    }

    /**
     * get username
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * get password
     * @return password
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * set current state.
     * @param state current state
     */
    public void setCurrentState(int state) {
        currentState = state;
    }

    /**
     * set the setup
     * @param setUp setup
     */
    public void setSetUp(Setup setUp){
        this.setUp = setUp;
    }

    /**
     * get current state.
     * @return current state.
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     *  get account
     * @return account
     */
    public Account getAccount() {
        return this;
    }

    /**
     * get player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * get setup
     * @return setup
     */
    public Setup getSetUp() {
        return setUp;
    }

    /**
     * This class is responsible for building an account.
     */
    public static class AccountBuilder {
        private String userName;
        private String passWord;
        private Player player;
        private int currentState;
        private Setup setUp;

        /**
         * account builder needs username and password to construct.
         * @param userName username
         * @param passWord password
         */
        public AccountBuilder(String userName, String passWord) {
            this.userName = userName;
            this.passWord = passWord;
        }

        /**
         * set player
         * @param player player
         * @return this accountbuilder.
         */
        public AccountBuilder setPlayer(Player player) {
            this.player = player;
            return this;
        }

        /**
         * ser current state
         * @param currentState current state
         * @return this accountbuilder.
         */
        public AccountBuilder setCurrentState(int currentState) {
            this.currentState = currentState;
            return this;
        }

        /**
         * set Setup
         * @param setUp setup
         * @return accountbuilder.
         */
        public AccountBuilder setSetUp(Setup setUp) {
            this.setUp = setUp;
            return this;
        }

        /**
         * Build a new account.
         * @return account after built
         */
        public Account build() {
            return new Account(this);
        }

    }


}

