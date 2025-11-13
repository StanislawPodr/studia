#zadanie 1
rzutMoneta5Reszek <- dbinom(5, 6, 0.5)
reszki3LubWiecej <- 1 - pbinom(2, 6, 0.5)
od2Do4Reszek <- pbinom(4, 6, 0.5) - pbinom(1, 6, 0.5)
plot(0:6, sapply(0:6, function(x) dbinom(x, 6, 0.5)), type = "h")

#zadanie2
dpois(5, 2)
1 - ppois(3, 2)
ppois(5, 2) - ppois(2, 2)
plot(0:30, sapply(0:30, function(x) dpois(x, 2)), type="h")

#zadanie3
dunif(5, 4, 12)
punif()
