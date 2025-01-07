document.addEventListener('DOMContentLoaded', function () {
    const generateButton = document.getElementById('generate');
    const animationContainer = document.getElementById('animation-container');
    const downloadLink = document.getElementById('download-link');
    let animation = null;

    generateButton.addEventListener('click', async function () {
        const image1Input = document.getElementById('image1');
        const image2Input = document.getElementById('image2');
        const file1 = image1Input.files[0];
        const file2 = image2Input.files[0];


        if (!file1 || !file2) {
            alert("Please select both images.");
            return;
        }


        const formData = new FormData();
        formData.append('image1', file1);
        formData.append('image2', file2);


         try {
            const response = await fetch('/generate_animation', {
                method: 'POST',
                body: formData
            });
           if (!response.ok) {
              const errorText = await response.text();
                throw new Error(`Server responded with ${response.status}: ${errorText}`);
            }
            const animationData = await response.json();

              if(animation) {
                animation.destroy();
              }
            animation = lottie.loadAnimation({
                container: animationContainer,
                renderer: 'svg',
                loop: true,
                autoplay: true,
                animationData: animationData
            });

           const jsonString = JSON.stringify(animationData, null, 4)
            const blob = new Blob([jsonString], { type: 'application/json' });
           downloadLink.href = URL.createObjectURL(blob);
           downloadLink.classList.remove('hidden');
        } catch (error) {
           console.error("Error fetching animation data: ", error);
           alert("Error fetching animation data. Please check the console for details.")
        }


    });
});
