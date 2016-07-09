/**
 * purpose- use the frequencies to decrypt a message
 * Created by admin on 4/4/16.
 */
public class FrequencyAnalysisTester 
{

	public static void main(String args[])
	{
		FrequencyAnalysis fa = new FrequencyAnalysis();
		fa.doAnalysis("ciphertext.txt","ciphertextfreq.txt");


	}


}
