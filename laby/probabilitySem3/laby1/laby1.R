a <- c(1, 4, 6, 13, -10, 8)
b <- seq(0, 102, by = 2)
c <- rep(c(4, 7, 9), each = 3)
d <- c("czy", "to", "jest", "wektor", NA)
e <- c("czy", "to", "jest", "wektor", "NA")
f <- rep(c(4, 7, 9), times = 6)
length(a); typeof(a); min(a); max(a); sum(a)
length(b); typeof(b); min(b); max(b); sum(b)
length(c); typeof(c); min(c); max(c); sum(c)
length(d); typeof(d); min(d); 
max(d, na.rm = TRUE); 
sum(d)
length(e); typeof(e); min(e); max(e); sum(e)
length(f); typeof(f); min(f); max(f); sum(f)
sort(d)
sort(e)
a_f <- a + f
a_times_f <- a * f
a_c <- a + c
a_10 <- a + 10
a_times_15 <- 15 * a
b26 <- b[26]
f6_to_10 <- f[6:10]
higher_than_50_in_b <- b[b > 50]
A <- matrix(c(3, 4, 1, 5, 2, 3), nrow = 2)
B <- cbind(c(-1, 3, -5), c(2, -4, 6))
C <- rbind(c(7, 3), c(2, 1))
D <- matrix(c(1, 3, 5, 2, 5, 7, 4, 7, 11), nrow = 3)
A_B <- A + B # błąd
A_Bt <- A + t(B)
A_times_B <- A %*% B
A_times_A_normal_mult <- A * A
D_minus_1 <- solve(D)
should_be_I <- D %*% D_minus_1
X <- solve(C) %*% A
Y <- A %*% solve(D)
a <- seq(300, 0, by = -3)
b <- c("one", "two", "three", "four", 5)
c <- c("one", "two", "three", "four", "5")
d <- c(3, 1, 6, 3, 1, 6, 3, 1, 6, 3, 1, 6)
e <- c(3, 3, 3, 3, 1, 1, 1, 1, 6, 6, 6, 6)
f <- c(5, 1, 4, 7)
length(a); typeof(a); min(a); max(a); sum(a)
length(b); typeof(b); min(b); max(b); sum(b)
length(c); typeof(c); min(c); max(c); sum(c)
length(d); typeof(d); min(d); max(d); sum(d)
length(e); typeof(e); min(e); max(e); sum(e)
length(f); typeof(f); min(f); max(f); sum(f)
b <- sort(b)
e <- sort(e)
df_sum <- d + f
dot_df <- sum(d * f)
elem_35 <- a[35]
slice_67_85 <- a[67:85]
cross_de <- c(
  d[2]*e[3] - d[3]*e[2],
  d[3]*e[1] - d[1]*e[3],
  d[1]*e[2] - d[2]*e[1]
)
less_than_100 <- a[a < 100]          
len_lt_100 <- length(less_than_100) 
sum_lt_100 <- sum(less_than_100)  
A <- matrix(c(-3, 4, 1, -5, -2, 3), ncol = 3)
B <- matrix(c(1, 3, 5, 2, 4, 6), ncol = 2)
C <- matrix(c(7, -2, -3, 1), ncol = 2)
D <- matrix(c(1, 3, 2, 2, 5, 3, 4, 7, 2), ncol = 3)

A_B <- A + B
A_t_B <- t(A) + B
BA <- A %*% B
B_times_b <- B * B
notC <- solve(C)
should_be_I <- C %*% solve(C)
X <- B %*% solve(C)
X <- solve(D) %*% B

