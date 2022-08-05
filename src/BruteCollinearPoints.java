import java.util.Arrays;

public class BruteCollinearPoints
{ 

    private Point[] _points;
    private LineSegment[] segmentos;
    private int segmentCounter; 

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
            throw new IllegalArgumentException("An Array with a null value is not permitted");
        int i = 1;
        for (Point p : points)
        {
            if (p == null)
                throw new IllegalArgumentException("A point with a null value is not permitted"); 
            for (int j = i; j < points.length; j++)
            {
                if (p==points[i])
                    throw new IllegalArgumentException("A repeated point is not permitted");   
            }
            i++;
        }     
        segmentCounter = 0;
        this._points = points;
        segmentos = new LineSegment[_points.length*2];
    }
    public int numberOfSegments()        // the number of line segments
    {
        if(_points.length < 2)
            return 0;
          
        return 1;
    }
    public LineSegment[] segments()                // the line segments
    {
        Arrays.sort(_points);
        if (_points.length==1)
            return new LineSegment[0]; 
      
        Double pendiente1 = 0.00;
        Double pendiente2 = 0.00;
        Double lineaPendiente = -999999999.00;
        LineSegment temp;
        int cont = 0;
      
        for (int i = 0; i < _points.length-1; i++)
        {
            pendiente1 = _points[i].slopeTo(_points[i+1]);
            temp = new LineSegment(_points[i], _points[i+1]);
            
            if(i+2 >= _points.length )
            {
                segmentos[segmentCounter]= temp;
                segmentCounter++;
                continue;
            }
            for (int j = i+2; j < _points.length; j++)
            {
                pendiente2 =_points[i].slopeTo(_points[j]);
                
            // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
            // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
            // |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
                if( Double.compare(pendiente1,pendiente2) == 0)
                {
                   if(pendiente1 == lineaPendiente)
                    continue;
                   temp = new LineSegment(_points[i], _points[j]);
                   lineaPendiente = pendiente1;                                         
                //    cont++;
                   
                }              
                
                
                else
                {
                    segmentos[segmentCounter] = new LineSegment(_points[i], _points[j]);
                    segmentCounter++;
                }

            }
            segmentos[segmentCounter] = temp;
            segmentCounter++;    
        }
        

        return segmentos;
    }

    
}
