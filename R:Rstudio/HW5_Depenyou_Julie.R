################################################
###  Script to help setup INST 314 Homework 5
### 
################################################

# Q1 - no code

# Q2 
# you will need to download & import the Fifa2017_FullData_(Kaggle).csv
library(readr)
Fifa2017 <- read_csv("Downloads/Fifa2017_FullData_(Kaggle).csv")


Fifa2017$foot_avg <-(Fifa2017$Shot_Power+Fifa2017$Finishing+
                  Fifa2017$Long_Shots+Fifa2017$Curve+Fifa2017$Freekick_Accuracy+
                  Fifa2017$Short_Pass+Fifa2017$Long_Pass) /7

summary(Fifa2017$foot_avg)
sd(Fifa2017$foot_avg)
summary(Fifa2017$foot_avg[Fifa2017$Club == 'FC Barcelona'])
sd(Fifa2017$foot_avg[Fifa2017$Club == 'FC Barcelona'])
summary(Fifa2017$foot_avg[Fifa2017$Club == 'Longford Town'])
sd(Fifa2017$foot_avg[Fifa2017$Club == 'Longford Town'])

#All players
xbar <- mean(Fifa2017$foot_avg); xbar
s <- sd(Fifa2017$foot_avg); s
n <- length(Fifa2017$foot_avg); n  # none missing
se <- s/sqrt(n-1); se
z <- qnorm(0.975); z
z*se
ci.upper <- xbar + z*se; ci.upper
ci.lower <- xbar - z*se; ci.lower

#FC Barcelona
xbar_fc <- mean(Fifa2017$foot_avg[Fifa2017$Club == 'FC Barcelona']); xbar_fc
s_fc <- sd(Fifa2017$foot_avg[Fifa2017$Club == 'FC Barcelona']); s
n_fc <- length(Fifa2017$foot_avg[Fifa2017$Club == 'FC Barcelona']); n_fc  # none missing
se_fc <- s_fc/sqrt(n_fc-1); se_fc
z_fc <- qnorm(0.975); z_fc
z_fc*se_fc
ci.upper_fc <- xbar_fc + z_fc*se_fc; ci.upper_fc
ci.lower_fc <- xbar_fc - z_fc*se_fc; ci.lower_fc

#Longford Town
xbar_lt <- mean(Fifa2017$foot_avg[Fifa2017$Club == 'Longford Town']); xbar_lt
s_lt <- sd(Fifa2017$foot_avg[Fifa2017$Club == 'Longford Town']); s_lt
n_lt <- length(Fifa2017$foot_avg[Fifa2017$Club == 'Longford Town']); n_lt  # none missing
se_lt <- s_fc/sqrt(n_fc-1); se_lt
z_lt <- qnorm(0.975); z_lt
z_lt*se_lt
ci.upper_lt <- xbar_lt + z_lt*se_lt; ci.upper_lt
ci.lower_lt <- xbar_lt - z_lt*se_lt; ci.lower_lt


# Q3
# first open GSS 2016 data

attach(gss)  # this allows you to call upon variables within a dataframe without first specifying the gss$

# Create an optimism scale variable using LOTR1 to LOTR6
# variables are currently categorical/factor & must be converted to quantitative/numeric
# original responses are Likert (SD to SA)
# Do you agree or disagree that...
# 1) In uncertain times, I usually expect the best.
LOTR1.r <- as.numeric(LOTR1)  # convert
table(LOTR1, LOTR1.r)         # confirm recode
# 2) If something can go wrong for me, it will.  (need to reverse code)
LOTR2.r <- as.numeric(LOTR2)  # convert
LOTR2.r <- 6 - LOTR2.r        # reverse code
table(LOTR2, LOTR2.r)         # confirm recode
# 3) I'm always optimistic about my future.
LOTR3.r <- as.numeric(LOTR3)  # convert
table(LOTR3, LOTR3.r)         # confirm recode
# 4)I hardly ever expect things to go my way.  (need to reverse code)
LOTR4.r <- as.numeric(LOTR4)  # convert
LOTR4.r <- 6 - LOTR4.r        # reverse code
table(LOTR4, LOTR4.r)         # confirm recode
# 5) I rarely count on good things happening to me. (need to reverse code)
LOTR5.r <- as.numeric(LOTR5)  # convert
LOTR5.r <- 6 - LOTR5.r        # reverse code
table(LOTR5, LOTR5.r)         # confirm recode
# 6)  Overall, I expect more good things to happen to me than bad.
LOTR6.r <- as.numeric(LOTR6)  # convert
table(LOTR6, LOTR6.r)         # confirm recode
# create & explore the LOTR scale variable
# note unlike the mean scale in question 2, we are NOT dividing by the total number of variables used.
# instead, we sum the variables then subtract the number of variables.  The subtraction resets the min value to 1.  This is for our ease of interpretation.
# where a mean scale has the same min & max as the input variables, the interval scale as the same min but the max is the sum of the input variable max values (longer range)
# this & the mean scale are mathematically similar, but have different uses for how we discuss & visualize data.
LOTR.scale <- (LOTR1.r + LOTR2.r + LOTR3.r + LOTR4.r + LOTR5.r + LOTR6.r) -5
hist(LOTR.scale, main="Optimism Scale", xlab="Level of Optimism" )

xbar_op <- mean(LOTR.scale, na.rm = TRUE); xbar_op
s_op <- sd(LOTR.scale, na.rm = TRUE); s_op
n_op <- 1441; n_op  # none missing
se_op <- s_op/sqrt(n_op-1); se_op
z_op <- qnorm(0.98); z_op
z_op*se_op
ci.upper_op <- xbar_op + z_op*se_op; ci.upper_op
ci.lower_op <- xbar_op - z_op*se_op; ci.lower_op

# End of setup file