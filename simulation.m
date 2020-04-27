parpool(12);
tic
sims = 10000;
%numCards = input("How many cards: ");
numCards = 7;
cnt1 = 0;
cnt2 = 0;
cnt3 = 0;
cnt4 = 0;
cnt5 = 0;
counter =0;
parfor i = 1:(sims*sims)
    myCards = randperm(52, numCards);
	cnt5 = cnt5 + FourKind(myCards,counter);
    cnt4 = cnt4 + FullHouse(myCards,counter);
    cnt3 = cnt3 + ThreeKind(myCards,counter);
    cnt2 = cnt2 + TwoPair(myCards,counter);
    cnt1 = cnt1 + Pair(myCards,counter);
end

counterVec = [cnt1,cnt2,cnt3,cnt4,cnt5];
percentages = counterVec./(sims*sims);
fprintf("%.4f%%\n",100.*percentages)
delete(gcp('nocreate'))
toc

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
    if (((cardsValue(5)>0)&&(cardsValue(4)>0)) || ((cardsValue(4)>0)&&(cardsValue(4)>0)) || ((cardsValue(4)>0)&&(cardsValue(3)>0)) || ((cardsValue(5)>0)&&(cardsValue(3)>0)))
    %if (((cardsValue(4)>0)&&(cardsValue(4)>0)) || ((cardsValue(4)>0)&&(cardsValue(3)>0)))
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
    if (cardsValue(4)>0)
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
    if (cardsValue(3)>1)
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
    if (cardsValue(3)>0)
        counter = counter + 1;
    end
end