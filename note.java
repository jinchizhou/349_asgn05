/*
Goal: pick up max amt of money
Constraint: Can't pick up consecutive "rewards"

// init: [5, 1, 2, 10, 6, 3]
// [5, 5, 7, 15, 15, 18]
// [18, 14, 13, 13, 6, 3]

DP: Max F(i) = max amt you can pick up if there are i rewards

pick up the curr with F(i - 2) or F(i - 1)
Recurrence Relation: F(i) = Max(val(i) + F(i - 2), F(i - 1))

In order to trace back what was picked up,
look at prev to see if same as mem, if not then curr mustve been picked up

*/
