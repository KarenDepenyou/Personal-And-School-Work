attach(gss)

child <- gss$CHILDS
hours <- gss$HRS1

install.packages("car")
library(car)

hours<- car::recode(gss$HRS1,"'89+ hrs'=89")
hours.fix <- as.numeric(as.character(hours))
child <- car::recode(gss$CHILDS,"'EIGHT OR MORE'=8")
child.fix <- as.numeric(as.character(child))

summary(hours.fix)
summary(child.fix)

sd(hours.fix, na.rm = TRUE)
sd(hchild.fix, na.rm = TRUE)

cor.test(child.fix,hours.fix)
summary(lm(child.fix ~ hours.fix))
workchild <- lm(child.fix ~ hours.fix)
par(mfrow = c(2,2))  # for viewing all the plots at once in a 2x2 grid
plot(workchild)
install.packages("stargazer")
library(stargazer)
stargazer(workchild,type = "html", out= "Downloads/314html.htm")
plot(hours.fix,child.fix, ylab="Number of Children", xlab="Hours Worked per Week", main="Hours Worked and Number of Children")
abline(lm(child.fix ~ hours.fix), col= "red",lwd=4)
