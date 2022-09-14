#q1
regular <- c(16, 20, 21, 22, 23, 22, 27, 25 , 27, 28); regular
premium <- c(19, 22, 24, 24, 25, 22, 26, 26 , 28, 32); premium

#calculate t-test by hand
x1 <- mean(regular); x1
x2 <- mean(premium); x2
sd1 <- sd(regular); sd1
sd2 <- sd(premium); sd2

df <- length(regular) + length(premium) -2
t <- (x1-x2) / sqrt( (sd1^2/10) + (sd2^2/10) )
p <- 2* pt(-abs(t), df); p

#q2
library(readr)
movie <- read_csv("Downloads/comicmovies.csv")
both <- movie$Worldwide; both
dc <- movie$Worldwide[movie$Studio == 'DC']; dc
marvel <- movie$Worldwide[movie$Studio == 'Marvel']; marvel

library(summarytools)
descr(both, show("min","max","mean","sd","n","iqr"))
descr(dc, show("min","max","mean","sd","n","iqr"))
descr(marvel, show("min","max","mean","sd","n","iqr"))

xdc <- mean(dc); xdc
xmarv <- mean(marvel); xmarv
sdc <- sd(dc); sdc
sdmarv <- sd(marvel); sdmarv
dfmov <- length(dc) + length(marvel) -2
tmov <- (xdc-xmarv) / sqrt((sdc^2/ length(dc))+ (sdmarv^2/ length(marvel))); tmov
pmov <- 2* pt(-abs(tmov), dfmov); pmov

cohens <- (xdc-xmarv)/ sqrt((sdc^2 + sdmarv^2)/2); cohens

#q3
pre_disney <- movie$Review[movie$Studio == "Marvel" & movie$Year < 2010]; pre_disney
disney <- movie$Review[movie$Studio == "Marvel" & movie$Year >= 2010]; disney
total <- movie$Review[movie$Studio == "Marvel"]; total

descr(total, show("min","max","mean","sd","n","iqr"))
descr(pre_disney, show("min","max","mean","sd","n","iqr"))
descr(disney, show("min","max","mean","sd","n","iqr"))

xp <- mean(pre_disney); xp
xd <- mean(disney); xd
sp <- sd(pre_disney); sp
sds <- sd(disney); sds
df1 <- length(pre_disney) + length(disney) -2
t1 <- (xp-xd) / sqrt((sp^2/ length(pre_disney))+ (sds^2/ length(disney))); t1
p1 <- 2* pt(-abs(t1), df1); p1

#q4
install.packages("pwr")
library(pwr)
pwr.norm.test(sig.level=0.05, power=NULL, alt="two.sided", n=63, d=0.5)

