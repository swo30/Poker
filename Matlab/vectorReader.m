a(1) = load('counterVec0','-mat');
a(2) = load('counterVec1','-mat');
a(3) = load('counterVec2Pair','-mat');
a(4) = load('counterVec2Singles','-mat');
a(5) = load('counterVec33K','-mat');
a(6) = load('counterVec3Pair','-mat');
a(7) = load('counterVec3Singles','-mat');
a(8) = load('counterVec42Pair','-mat');
a(9) = load('counterVec43K','-mat');
a(10) = load('counterVec44K','-mat');
a(11) = load('counterVec4Pair','-mat');
a(12) = load('counterVec4Singles','-mat');
a(13) = load('counterVec52Pairs','-mat');
a(14) = load('counterVec53K','-mat');
a(15) = load('counterVec54K','-mat');
a(16) = load('counterVec5FH','-mat');
a(17) = load('counterVec5Pair','-mat');
a(18) = load('counterVec5Singles','-mat');
a(19) = load('counterVec62Pair','-mat');
a(20) = load('counterVec63K','-mat');
a(21) = load('counterVec63Pair','-mat');
a(22) = load('counterVec64K','-mat');
a(23) = load('counterVec6FH','-mat');
a(24) = load('counterVec6Pair','-mat');
a(25) = load('counterVec6Singles','-mat');

for i=0:6
    denom(i+1) = nchoosek(52-i,7-i);
end

b = {a.counterVec};

for i=1:25
    CounterVector = b{1,i};
    sumOfChances(i) = CounterVector(3); %3 for 3 of a kind
    
                    percentage = 100*sumOfChances(i)/denom(0+1)
    if i==2         percentage = 100*sumOfChances(i)/denom(1+1), end
    if i>=3&&i<=4   percentage = 100*sumOfChances(i)/denom(2+1), end
    if i>=5&&i<=7   percentage = 100*sumOfChances(i)/denom(3+1), end
    if i>=8&&i<=12  percentage = 100*sumOfChances(i)/denom(4+1), end
    if i>=13&&i<=18 percentage = 100*sumOfChances(i)/denom(5+1), end
    if i>=19        percentage = 100*sumOfChances(i)/denom(6+1), end
end
