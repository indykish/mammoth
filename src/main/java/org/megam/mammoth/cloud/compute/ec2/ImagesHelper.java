package org.megam.mammoth.cloud.compute.ec2;

import java.util.List;

import org.megam.mammoth.cloud.compute.exception.ComputeEngineException;
import org.megam.mammoth.cloud.compute.info.CloudInstance;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Image;

class ImagesHelper {

	private DescribeImagesRequest request;
	private AmazonEC2Client client;

	ImagesHelper(AmazonEC2Client tempClient, DescribeImagesRequest tempRequest) {
		this.client = tempClient;
		this.request = tempRequest;

	}

	CloudInstance images(CloudInstance tempInstance)
			throws ComputeEngineException {
		try {
			DescribeImagesResult result = client.describeImages(request);
			List<Image> imagesList = result.getImages();
			if (!imagesList.isEmpty()) {
				Image singleImage = imagesList.get(0);
				tempInstance.setDescription(singleImage.getDescription());
				tempInstance.setName(singleImage.getName());
			}

		} catch (AmazonServiceException ase) {
			throw new ComputeEngineException(ase);

		} catch (AmazonClientException ase) {
			throw new ComputeEngineException(ase);
		}
		return tempInstance;
	}

}
