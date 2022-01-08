## Individual Analysis Report


The results of first and second methods (/post and /postwithref) are the same. 10000 requests are successful and t < 800ms for both of them. Some of requests to third route are failed and t > 1200ms. The reason of third route (/postandref) failures is that it creates a reference separately in another table and links it with foreign key via POST which takes more time.