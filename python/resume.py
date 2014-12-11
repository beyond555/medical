import csv
import sys
def run(inputFile,outputFile,AuthorMap,agencyMap):
	csvfile = file(inputFile, 'rb')
	save=sys.stdout
	fsock=open(outputFile,'w')
	sys.stdout=fsock
	reader = csv.reader(csvfile)
	for line in reader:
		str=''.join(line)
		ans=str.split("\t")
		key=AuthorMap.get(ans[0])
		print key+"\t"+agencyMap.get(key)+"\t"+ans[1]
	csvfile.close()
	fsock.close()
	sys.stdout=save

