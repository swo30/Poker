tic
parpool(12);
clear; clc;
counter = 0;
myCards = [];
suits = ceil(myCards./13);
values = myCards - 13.*(suits-1);

if isempty(myCards)
    AllPossibilities = readmatrix('C.csv');
else
    C = combnk(1:52,7-length(myCards));
    lia = ismember(C,myCards);
    rowsDel = any(lia,2);
    C(rowsDel,:) = [];
    myCardsRepeat=repmat(myCards,length(C),1);
    AllPossibilities = [myCardsRepeat C];
end

if isempty(AllPossibilities) %only on 7 cards
    denom = 1;
    StraightFlush(myCards,counter);
    FourKind(myCards,counter);
    FullHouse(myCards,counter);
    Flush(myCards,counter);
    Straight(myCards,counter);
    ThreeKind(myCards,counter);
    TwoPair(myCards,counter);
    Pair(myCards,counter);
else
    cnt1=0;cnt2=0;cnt3=0;cnt4=0;cnt5=0;cnt6=0;cnt7=0;cnt8=0;
    denom = length(AllPossibilities);
    parfor i = 1:length(AllPossibilities)
        cnt8 = cnt8 + StraightFlush(AllPossibilities(i,:),counter);
        cnt7 = cnt7 + FourKind(AllPossibilities(i,:),counter);
        cnt6 = cnt6 + FullHouse(AllPossibilities(i,:),counter);
        cnt5 = cnt5 + Flush(AllPossibilities(i,:),counter);
        cnt4 = cnt4 + Straight(AllPossibilities(i,:),counter);
        cnt3 = cnt3 + ThreeKind(AllPossibilities(i,:),counter);
        cnt2 = cnt2 + TwoPair(AllPossibilities(i,:),counter);
        cnt1 = cnt1 + Pair(AllPossibilities(i,:),counter);
    end
end

counterVec = [cnt1,cnt2,cnt3,cnt4,cnt5,cnt6,cnt7,cnt8];
percentages = zeros(1,length(counterVec));
for i=1:length(counterVec)
    percentages(i) = 100.*counterVec(i)./denom;
    fprintf("%.4f%%\n", percentages(i))
end
toc
delete(gcp('nocreate'))

save('counterVec0.mat','counterVec');
%example = matfile('counterVec1.mat');
% counterVec = example.counterVec;

function counter = StraightFlush(myCards,counter)
    suits = ceil(myCards./13);
    [~,F] = mode(suits);
    if (F>=5)
        for i=1:4
            suits = ceil(myCards./13);
            typeI = myCards;
            suitI = ismember(suits,i);
            typeI(~suitI) = [];
            suits(~suitI) = [];
            if ~isempty(typeI)
                values = typeI - 13.*(suits-1);
                
                values = unique(values,'first'); %sort
                if (values(1) == 1)
                    values = [values 14];
                end
                N = 4; % Required number of consecutive numbers following a first one
                x = diff(values)==1;
                f = find([false,x]~=[x,false]);
                g = find(f(2:2:end)-f(1:2:end-1)>=N,1,'first');
                first_t = values(f(2*g-1)); % First t followed by >=N consecutive numbers
                if (~isempty(first_t))
                    counter = counter + 1;
                    break;
                end
            end
        end

    end
end

function counter = Straight(myCards,counter)
    suits = ceil(myCards./13);
    values = myCards - 13.*(suits-1);
    values = unique(values,'first');
    if (values(1) == 1)
        values = [values 14];
    end
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
    [~,F] = mode(suits);
    if (F>=5)
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