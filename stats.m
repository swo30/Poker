tic
clear; clc;
counter = 0;
myCards = [1 2 3 4 14 15];

C = combnk(1:52,7-length(myCards));
lia = ismember(C,myCards);
rowsDel = any(lia,2);
C(rowsDel,:) = [];

myCardsRepeat=repmat(myCards,length(C),1);
AllPossibilities = [myCardsRepeat C];
counterVec = zeros(1,7);

if isempty(AllPossibilities)
    denom = 1;
    FourKind(myCards,counter);
    FullHouse(myCards,counter);
    Straight(myCards,counter);
    Flush(myCards,counter);
    ThreeKind(myCards,counter);
    TwoPair(myCards,counter);
    Pair(myCards,counter);
else
    denom = length(AllPossibilities);
    for i = 1:length(AllPossibilities)
        counterVec(7) = counterVec(7) + FourKind(AllPossibilities(i,:),counter);
        counterVec(6) = counterVec(6) + FullHouse(AllPossibilities(i,:),counter);
        counterVec(5) = counterVec(5) + Straight(AllPossibilities(i,:),counter);
        counterVec(4) = counterVec(4) + Flush(AllPossibilities(i,:),counter);
        counterVec(3) = counterVec(3) + ThreeKind(AllPossibilities(i,:),counter);
        counterVec(2) = counterVec(2) + TwoPair(AllPossibilities(i,:),counter);
        counterVec(1) = counterVec(1) + Pair(AllPossibilities(i,:),counter);
    end
end

percentages = zeros(1,length(counterVec));
for i=1:length(counterVec)
    percentages(i) = 100.*counterVec(i)./denom;
    fprintf("%.4f%%\n", percentages(i))
end
toc


function counter = Straight(myCards,counter)
    suits = ceil(myCards./13);
    values = myCards - 13.*(suits-1);
    values = unique(values,'first');
    N = 4; % Required number of consecutive numbers following a first one
    x = diff(values)==1;
    f = find([false,x]~=[x,false]);
    g = find(f(2:2:end)-f(1:2:end-1)>=N,1,'first');
    first_t = values(f(2*g-1)); % First t followed by >=N consecutive numbers
    if (~isempty(first_t))
        counter = counter + 1;
    end
end

function counter = Flush(myCards,counter)
    suits = ceil(myCards./13);
    flush = 0;
    for i = 1:length(suits)
        if (suits(1) == suits(i))
            flush = flush + 1;
        end
    end
    if (flush>=5)
        counter = counter + 1;
    end
end

function counter = FourKind(myCards,counter)
    suits = ceil(myCards./13);
    values = myCards - 13.*(suits-1);

    cardsValue = [0,0,0,0,0]; %%Non Values,singles,pairs,3kind,4kind
    map = [0,0,0,0,0,0,0,0,0,0,0,0,0];

    for i = 1:length(myCards)
        map(values(i)) = map(values(i)) + 1;
    end

    for i = 1:13
        cardsValue(map(i)+1) = cardsValue(map(i)+1) + 1;
    end
    if (cardsValue(5)>0)
        counter = counter + 1;
    end
end

function counter = FullHouse(myCards,counter)
	suits = ceil(myCards./13);
    values = myCards - 13.*(suits-1);

    cardsValue = [0,0,0,0,0]; %%Non Values,singles,pairs,3kind,4kind
    map = [0,0,0,0,0,0,0,0,0,0,0,0,0];

    for i = 1:length(myCards)
        map(values(i)) = map(values(i)) + 1;
    end

    for i = 1:13
        cardsValue(map(i)+1) = cardsValue(map(i)+1) + 1;
    end
    % 4k&3k or 3k&3k or 3k&pair or 4k&pair
    if (((cardsValue(5)>0)&&(cardsValue(4)>0)) || (cardsValue(4)==2) || ((cardsValue(4)>0)&&(cardsValue(3)>0)) || ((cardsValue(5)>0)&&(cardsValue(3)>0)))
    %if ((cardsValue(4)== 2) || ((cardsValue(4)>0)&&(cardsValue(3)>0)))
        counter = counter + 1;
    end
end

function counter = ThreeKind(myCards,counter)
    suits = ceil(myCards./13);
    values = myCards - 13.*(suits-1);

    cardsValue = [0,0,0,0,0]; %%Non Values,singles,pairs,3kind,4kind
    map = [0,0,0,0,0,0,0,0,0,0,0,0,0];

    for i = 1:length(myCards)
        map(values(i)) = map(values(i)) + 1;
    end

    for i = 1:13
        cardsValue(map(i)+1) = cardsValue(map(i)+1) + 1;
    end
    if ((cardsValue(4)>0)||((cardsValue(5)>0)))
        counter = counter + 1;
    end
end

function counter = TwoPair(myCards,counter)
    suits = ceil(myCards./13);
    values = myCards - 13.*(suits-1);

    cardsValue = [0,0,0,0,0]; %%Non Values,singles,pairs,3kind,4kind
    map = [0,0,0,0,0,0,0,0,0,0,0,0,0];

    for i = 1:length(myCards)
        map(values(i)) = map(values(i)) + 1;
    end

    for i = 1:13
        cardsValue(map(i)+1) = cardsValue(map(i)+1) + 1;
    end
    if ((cardsValue(3)>1)||((cardsValue(4)>0)&&cardsValue(3)>0)||(cardsValue(5)>0))
        counter = counter + 1;
    end
end

function counter = Pair(myCards,counter)
    suits = ceil(myCards./13);
    values = myCards - 13.*(suits-1);

    cardsValue = [0,0,0,0,0]; %%Non Values,singles,pairs,3kind,4kind
    map = [0,0,0,0,0,0,0,0,0,0,0,0,0];

    for i = 1:length(myCards)
        map(values(i)) = map(values(i)) + 1;
    end

    for i = 1:13
        cardsValue(map(i)+1) = cardsValue(map(i)+1) + 1;
    end
    if ((cardsValue(3)>0)||(cardsValue(4)>0)||(cardsValue(5)>0))
        counter = counter + 1;
    end
end