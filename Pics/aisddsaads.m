clc
file = 'counterVec0'
% file = 'counterVec1'
% file = 'counterVec2Pair'
% file = 'counterVec2Singles'
% file = 'counterVec33K'
% file = 'counterVec3Pair'
% file = 'counterVec3Singles'
% file = 'counterVec42Pair'
% file = 'counterVec43K'
% file = 'counterVec44K'
% file = 'counterVec4Pair'
% file = 'counterVec4Singles'
% file = 'counterVec52Pairs'
% file = 'counterVec53K'
% file = 'counterVec54K'
% file = 'counterVec5FH'
% file = 'counterVec5Pair'
% file = 'counterVec5Singles'
% file = 'counterVec62Pair'
% file = 'counterVec63K'
% file = 'counterVec63Pair'
% file = 'counterVec64K'
% file = 'counterVec6FH'
% file = 'counterVec6Pair'
% file = 'counterVec6Singles'

load(file,'-mat')
a = 100*counterVec/133784560;
fprintf("%.5f\n",a)