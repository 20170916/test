package com.lo.docker;


import com.google.common.io.Resources;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.Image;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class DockerImageManager {
    private DefaultDockerClient defaultDockerClient = new DefaultDockerClient("unix:///var/run/docker.sock");;

    public void build() throws URISyntaxException, InterruptedException, DockerException, IOException {
        //final Path dockerDirectory = getResource("dockerDirectory");
        final Path dockerDirectory = new File("/home/sfapp/test-image").toPath();
        final AtomicReference<String> imageIdFromMessage = new AtomicReference<>();

        final String returnedImageId = defaultDockerClient.build(dockerDirectory, "test", message -> {
            final String imageId = message.buildImageId();
            if (imageId != null) {
                imageIdFromMessage.set(imageId);
            }
        });
    }

    public void listImages() throws DockerException, InterruptedException {
        final List<Image> images = defaultDockerClient.listImages(DockerClient.ListImagesParam.withLabel("reference","busy*"));
        System.out.println(images);

    }

    public void listContainers() throws DockerException, InterruptedException {
        final List<Container> containers = defaultDockerClient.listContainers();
        System.out.println(containers);
    }

    private static Path getResource(String name) throws URISyntaxException {
        // Resources.getResources(...).getPath() does not work correctly on windows,
        // hence this workaround.  See: https://github.com/spotify/docker-client/pull/780
        // for details
        return Paths.get(Resources.getResource(name).toURI());
    }

    public static void main(String[] args) {
        try {
            new DockerImageManager().listImages();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
