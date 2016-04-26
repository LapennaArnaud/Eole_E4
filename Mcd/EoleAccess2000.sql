#==============================================================
# Nom de SGBD :  Microsoft Access 2000 (Deprecated)
# Date de création :  16/11/2015 09:04:48
#==============================================================


RemoveJoin C=FK_PARTICIP_DF_SKIPPER T=PARTICIPER P=SKIPPER;

RemoveJoin C=FK_PARTICIP_REGPART_REGATE T=PARTICIPER P=REGATE;

RemoveJoin C=FK_PARTICIP_VOILPART_VOILIER T=PARTICIPER P=VOILIER;

RemoveTble C=PARTICIPER;

RemoveTble C=REGATE;

RemoveTble C=SKIPPER;

RemoveTble C=VOILIER;

#==============================================================
# Table : PARTICIPER
#==============================================================
CreateTble C=PARTICIPER N="Participer"
(
   C=TMPSREEL T="DATETIME" P=Yes M=Yes N="tmpsReel" Z=false,
   C=NUMREGATE T="LONG" P=Yes M=Yes N="numRegate" Z=false,
   C=NUMVOIL T="LONG" P=Yes M=Yes N="numVoil" Z=false,
   C=NUMSKIP T="LONG" P=No M=Yes N="numSkip" Z=false
);

#==============================================================
# Table : REGATE
#==============================================================
CreateTble C=REGATE N="Regate"
(
   C=NUMREGATE T="LONG" P=Yes M=Yes N="numRegate" Z=false,
   C=NOMREGATE T="NOTE" P=No M=Yes N="nomRegate" Z=false,
   C=DISTANCE T="DOUBLE" P=No M=Yes N="distance" Z=false,
   C="DATE" T="DATETIME" P=No M=Yes N="date" Z=false
);

#==============================================================
# Table : SKIPPER
#==============================================================
CreateTble C=SKIPPER N="Skipper"
(
   C=NUMSKIP T="LONG" P=Yes M=Yes N="numSkip" Z=false,
   C=NOMSKIP T="NOTE" P=No M=Yes N="nomSkip" Z=false,
   C=PRENOMSKIP T="NOTE" P=No M=Yes N="prenomSkip" Z=false
);

#==============================================================
# Table : VOILIER
#==============================================================
CreateTble C=VOILIER N="Voilier"
(
   C=NUMVOIL T="LONG" P=Yes M=Yes N="numVoil" Z=false,
   C=NOMVOIL T="NOTE" P=No M=Yes N="nomVoil" Z=false,
   C=CLASSE T="SMALLINT" P=No M=Yes N="classe" Z=false,
   C=RATING T="LONG" P=No M=Yes N="rating" Z=false
);

CreateJoin C=FK_PARTICIP_DF_SKIPPER T=PARTICIPER P=SKIPPER
(
      P=NUMSKIP F=NUMSKIP
);

CreateJoin C=FK_PARTICIP_REGPART_REGATE T=PARTICIPER P=REGATE
(
      P=NUMREGATE F=NUMREGATE
);

CreateJoin C=FK_PARTICIP_VOILPART_VOILIER T=PARTICIPER P=VOILIER
(
      P=NUMVOIL F=NUMVOIL
);

