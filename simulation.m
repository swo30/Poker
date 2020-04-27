parpool(12);
tic
sims = 10000;
%numCards = input("How many cards: ");
numCards = 5;
counterVec = zeros(1,sims/10);

parfor i = 1:(sims)
    counter = 0;
    for j = 1:(sims)
        myCards = randperm(52, numCards);
        counter = FullHouse(myCards,counter);
    end
    counterVec(i) = counter;
end

percentage = sum(counterVec,'all')/(sims*sims);
fprintf("%.4f%%\n",100*percentage)
delete(gcp('nocreate'))
toc

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
    if ( ((cardsValue(5)>0)&&(cardsValue(4)>0)) || ((cardsValue(4)>0)&&(cardsValue(4)>0)) || ((cardsValue(4)>0)&&(cardsValue(3)>0)) || ((cardsValue(5)>0)&&(cardsValue(3)>0)))
        counter = counter + 1;
    end
end

